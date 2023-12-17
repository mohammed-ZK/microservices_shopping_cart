package com.example.api_gateway_server.filter;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoggingFilter implements WebFilter {

	private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

//	@Autowired
//	private RabbitMQProducer mqProducer;

	@Override
//	@Async
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();

		log.info("REQUEST0.1");
		logRequestBody(request);
		log.info("REQUEST0.2");

		return chain.filter(exchange).doOnTerminate(() -> {
//			Audit audit = new Audit();
//			audit.setHttpServletRequest(exchange.getRequest().toString());
//			audit.setHttpServletResponse(exchange.getResponse().toString());
//			audit.setHttpCode(exchange.getResponse().getStatusCode().value());
			log.info("RESPONSE");
//			logResponseBody(response);
			log.info("RESPONSE");

		});
//		return chain.filter(exchange);
	}

//	private Mono<Void> logRequestBody(ServerWebExchange exchange) {
//		return exchange.getRequest().getBody().map(dataBuffer -> {
//			byte[] bytes = new byte[dataBuffer.readableByteCount()];
//			dataBuffer.read(bytes);
//			return new String(bytes, 0, bytes.length);
//		}).doOnNext(requestBody -> {
//			// Log the request body
//			System.out.println("Request Body: " + requestBody);
//			log.info("REQUEST --->" + requestBody);
//		}).then();
//	}
//
//	private void logResponseBody(ServerWebExchange exchange) {
//		exchange.getResponse().beforeCommit(() -> {
//			// Log the response body before committing the response
//			return Mono.fromRunnable(() -> {
//				((ReactiveHttpInputMessage) exchange.getResponse()).getBody().map(dataBuffer -> {
//					byte[] bytes = new byte[dataBuffer.readableByteCount()];
//					dataBuffer.read(bytes);
//					return new String(bytes, 0, bytes.length);
//				}).doOnNext(responseBody -> {
//					// Log the response body
//					log.info("RESPONSE --->" + responseBody);
//				}).subscribe();
//			});
//		});
//	}

	private void logRequestBody(ServerHttpRequest request) {
		log.info("REQUEST1");
		Flux<DataBuffer> body = request.getBody();
		log.info("REQUEST2");

		body.subscribe(dataBuffer -> {
			log.info("REQUEST3");
			byte[] bytes = new byte[dataBuffer.readableByteCount()];
			log.info("REQUEST4");
			dataBuffer.read(bytes);
			log.info("REQUEST5");
			log.info("REQUEST --->" + new String(bytes));
			log.info("REQUEST6");
		});
		log.info("REQUEST7");
	}

//	private void logResponseBody(ServerHttpResponse response) {
//		String responseBody = "Response body content";
//		log.info("RSPONSE1");
//		Mono<Void> originalMono = Mono.defer(() -> {
//			log.info("RSPONSE2");
//			String responseBody2 = "Hello, World!";
//			response.getHeaders().add("Content-Type", "text/plain");
//			log.info("RSPONSE3");
//			return response.writeWith(Mono.just(response.bufferFactory().wrap(responseBody2.getBytes())));
//		});
//
//		originalMono.doOnSuccess(success -> {
//			log.info("RSPONSE4");
//			byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
//			response.bufferFactory().wrap(bytes);
//			log.info("RSPONSE5");
//			log.info("RESPONSE --->" + responseBody);
//		});
//
//	}
}