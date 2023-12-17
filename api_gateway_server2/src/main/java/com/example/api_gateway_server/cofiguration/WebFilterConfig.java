package com.example.api_gateway_server.cofiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebFilterConfig {

	private static final Logger log = LoggerFactory.getLogger(WebFilterConfig.class);

	@Bean
	public WebClient webClient() {
		log.info("=======2");
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient())).build();
	}

	@Bean
	public HttpClient httpClient() {
		log.info("=======3");
		return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
	}
}
