package com.example.api_gateway_server.filter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import com.example.api_gateway_server.entity.Audit;
import com.example.api_gateway_server.service.RabbitMQProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Component
//@Order(-1)
public class LogRequestBodyGlobalFilter implements GlobalFilter {
	private final List<HttpMessageReader<?>> messageReaders = getMessageReaders();

	private static final Logger log = LoggerFactory.getLogger(LogRequestBodyGlobalFilter.class);


	private String ResponseBody = null;
	private String RequestBody = null;
	private int codeStatus = 0;

	private Audit audit = new Audit();

	@Autowired
	private RabbitMQProducer mqProducer;
	
	private List<HttpMessageReader<?>> getMessageReaders() {
		return HandlerStrategies.withDefaults().messageReaders();
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// Only apply on POST requests

		if (HttpMethod.POST.equals(exchange.getRequest().getMethod())) {
//			addResponseCaptureWrapper(exchange,chain);
			return logRequestBody(exchange, chain);
//			chain.filter(exchange.mutate().request(decoratedRequest).response(decoratedResponse).build());
		} else {
			return chain.filter(exchange);
		}
	}

	private Mono<Void> logRequestBody(ServerWebExchange exchange, GatewayFilterChain chain) {
		return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
			DataBufferUtils.retain(dataBuffer);

			Flux<DataBuffer> cachedFlux = Flux
					.defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));

			ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
				@Override
				public Flux<DataBuffer> getBody() {
					return cachedFlux;
				}
			};

			return ServerRequest
					// must construct a new exchange instance, same as below
					.create(exchange.mutate().request(mutatedRequest).build(), messageReaders).bodyToMono(String.class)
					.flatMap(body -> {
						// do what ever you want with this body string, I logged it.
						log.info("Request body : {}", body);
						// by putting reutrn statement in here, urge Java to execute the above
						// statements
						// put this final return statement outside then you'll find out that above
						// statements inside here are not executed.
						RequestBody=body;
//						log.info(responseBody);
//						ResponseBody = responseBody;
//						codeStatus = response.getStatusCode().value();
						log.info("Request " + RequestBody);
						log.info("Response " + ResponseBody);
						log.info("Code " + codeStatus);
						if (RequestBody == null) {
							RequestBody = "The Request is Empty";
						}
						audit.setHttp_code(codeStatus);
						audit.setRequest(RequestBody);
						audit.setResponse(ResponseBody);
						audit.setTime_stamp(new Timestamp(System.currentTimeMillis()));
						ObjectMapper objectMapper = new ObjectMapper();
						try {
							String jsonString = objectMapper.writeValueAsString(audit);
							mqProducer.sendMessage(jsonString);
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return chain.filter(exchange.mutate().request(mutatedRequest).build());
					});
		});
	}
}
