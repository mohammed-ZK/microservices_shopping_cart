package com.example.api_gateway_server.cofiguration;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.example.api_gateway_server.entity.Audit;
import com.example.api_gateway_server.service.RabbitMQProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PostGlobalFilter implements  GlobalFilter,WebFilter {

	private static final Logger log = LoggerFactory.getLogger(PostGlobalFilter.class);

	private String ResponseBody = null;
	private String RequestBody = null;
	private int codeStatus = 0;

	private Audit audit = new Audit();

	@Autowired
	private RabbitMQProducer mqProducer;
	
	private final List<HttpMessageReader<?>> messageReaders = getMessageReaders();

	private List<HttpMessageReader<?>> getMessageReaders() {
		return HandlerStrategies.withDefaults().messageReaders();
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		return logRequestBody(exchange, chain);

	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		ServerHttpResponse response = exchange.getResponse();
		DataBufferFactory dataBufferFactory = response.bufferFactory();
		ServerHttpResponseDecorator decoratedResponse = getDecoratedResponse(response, dataBufferFactory);
		return chain.filter(exchange.mutate().response(decoratedResponse).build());
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
					.create(exchange.mutate().request(mutatedRequest).build(), messageReaders).bodyToMono(String.class)
					.flatMap(body -> {
						log.info("Request body : {}", body);
						RequestBody = body;
						return chain.filter(exchange.mutate().request(mutatedRequest).build());
					});
		});

//		try {
//			return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
//			    DataBufferUtils.retain(dataBuffer);
//			    Flux<DataBuffer> cachedFlux = Flux.defer(() -> extracted(dataBuffer));
//			    ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
//			        @Override
//			        public Flux<DataBuffer> getBody() {
//			            return cachedFlux;
//			        }
//			    };
//
//			    return ServerRequest
//			            .create(exchange.mutate().request(mutatedRequest).build(), messageReaders)
//			            .bodyToMono(String.class)
//			            .flatMap(body -> {
//			                if (body == null || body.trim().isEmpty()) {
//			                    // Empty request body
//			                    log.info("Empty request body");
//			                    // You can handle this case or simply return an error response
//			                    return Mono.error(new RuntimeException("Empty request body"));
//			                } else {
//			                    // Non-empty request body
//			                    log.info("Request body: {}", body);
//			                    RequestBody = body;
//			                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
//			                }
//			            });
//			});
//		} catch (Exception e2) {
//			log.info("Excption is :{}"+e2);
//			return null;
//		}
		
	}

//	@SuppressWarnings("deprecation")
//	private Flux<DataBuffer> extracted(DataBuffer dataBuffer) {
//		return Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount()));
//	}

	private ServerHttpResponseDecorator getDecoratedResponse(ServerHttpResponse response,
			DataBufferFactory dataBufferFactory) {
		return new ServerHttpResponseDecorator(response) {

			@Override
			public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body) {

				if (body instanceof Flux) {

					Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;

					return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
						DefaultDataBuffer joinedBuffers = new DefaultDataBufferFactory().join(dataBuffers);
						byte[] content = new byte[joinedBuffers.readableByteCount()];
						joinedBuffers.read(content);

						String responseBody = new String(content, StandardCharsets.UTF_8);// MODIFY RESPONSE and Return
																							// the Modified response
						System.out.println(" response body :" + responseBody);			// the Modified response
						System.out.println(" response body :" + content);
						log.info(responseBody);
						ResponseBody = responseBody;
						codeStatus = response.getStatusCode().value();
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
						return dataBufferFactory.wrap(responseBody.getBytes());
					})).onErrorResume(err -> {

						log.info("5");
						System.out.println("error while decorating Response: {}" + err.getMessage());
						return Mono.empty();
					});

				}

				return super.writeWith(body);
			}
		};

	}

}