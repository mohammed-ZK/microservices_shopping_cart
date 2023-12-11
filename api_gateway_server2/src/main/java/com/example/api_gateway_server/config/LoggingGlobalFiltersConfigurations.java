package com.example.api_gateway_server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.scheduling.annotation.Async;

import com.example.api_gateway_server.entity.Audit;
import com.example.api_gateway_server.service.RabbitMQProducer;

import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class LoggingGlobalFiltersConfigurations {

	private static final Logger log = LoggerFactory.getLogger(LoggingGlobalFiltersConfigurations.class);

	@Autowired
	private RabbitMQProducer mqProducer;

	@Bean // @Order(0)

	@Async
	public GlobalFilter requestResponseGlobalFilter() {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest(); 
			log.info("=======>2"); 
//			var testParam = request.getBody(); 
			var testParam =request.getURI();
			if (testParam.equals("throwException")) { 
				throw new RuntimeException("param::" + testParam);
			} else { //
				log.info("=======>" + testParam);
			}

			return chain.filter(exchange) // response processing logic
					.then(Mono.fromRunnable(() -> {
						var response = exchange.getResponse();
						response.setRawStatusCode(200);
						log.info("=======>3" + response);
						Audit audit = new Audit(request.getURI().toString(), response.toString(),
								HttpStatus.OK.value());
						mqProducer.sendMessage(audit);

						log.info("=======>4");
						exchange.mutate().response(response).build();
					}));
		};
	}

}

/*
 * public class GlobalRequestFilter implements GlobalFilter, Ordered { ////post
 * 
 * @Override public Mono<Void> filter(ServerWebExchange exchange,
 * GatewayFilterChain chain) { ServerHttpRequest request =
 * exchange.getRequest(); var testParam =
 * request.getQueryParams().get("testParam").get(0);
 * if(testParam.equals("throwException")){ throw new
 * RuntimeException("param::"+testParam); } return chain.filter(exchange); }
 * 
 * @Override public int getOrder() { return -2; } } //////pre public class
 * GlobalResponseFilter implements GlobalFilter, Ordered {
 * 
 * @Override public Mono<Void> filter(ServerWebExchange exchange,
 * GatewayFilterChain chain) {
 * 
 * return chain.filter(exchange).then(Mono.fromRunnable(()->{ var response =
 * exchange.getResponse(); response.setRawStatusCode(201);
 * exchange.mutate().response(response).build(); })); }
 * 
 * @Override public int getOrder() { return 0; } }
 * 
 * //////pre post
 * 
 * @Configuration public class FilterConfig {
 * 
 * @Bean
 * 
 * @Order(-1) public GlobalFilter requestFilter(){ return (exchange, chain) -> {
 * ServerHttpRequest request = exchange.getRequest(); var testParam =
 * request.getQueryParams().get("testParam").get(0);
 * if(testParam.equals("throwException")){ throw new
 * RuntimeException("param::"+testParam); } return chain.filter(exchange); }; }
 * 
 * @Bean
 * 
 * @Order(-2) public GlobalFilter resFilter(){ return (exchange, chain) ->
 * chain.filter(exchange) .then(Mono.fromRunnable(() -> { var response =
 * exchange.getResponse(); response.setRawStatusCode(201);
 * exchange.mutate().response(response).build(); })); }
 * 
 * 
 * }
 * 
 * 
 * ///pre post ///
 * 
 * @Configuration public class FilterConfig {
 * 
 * @Bean
 * 
 * @Order(0) public GlobalFilter requestResponseGlobalFilter(){ return
 * (exchange, chain) -> { //request processing logic ServerHttpRequest request =
 * exchange.getRequest(); var testParam =
 * request.getQueryParams().get("testParam").get(0); if
 * (testParam.equals("throwException")) { throw new RuntimeException("param::" +
 * testParam); }
 * 
 * return chain.filter(exchange) //response processing logic
 * .then(Mono.fromRunnable(() -> { var response = exchange.getResponse();
 * response.setRawStatusCode(201); exchange.mutate().response(response).build();
 * })); }; }
 * 
 * }
 * 
 * 
 * @Configuration public class LoggingGlobalFiltersConfigurations {
 * 
 * final Logger logger = LoggerFactory.getLogger(
 * LoggingGlobalFiltersConfigurations.class);
 * 
 * @Bean public GlobalFilter postGlobalFilter() { logger.debug("========>");
 * return (exchange, chain) -> { return chain.filter(exchange)
 * .then(Mono.fromRunnable(() -> { logger.info("Global Post Filter executed");
 * })); }; } }
 * 
 * 
 * 
 * 
 * 
 */
