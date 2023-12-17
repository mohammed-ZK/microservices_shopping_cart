package com.example.api_gateway_server.cofiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@Configuration
public class AppConfig {
	
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	public RestTemplate template() {
		log.debug("hi I am RestTemplet ??????");
		return new RestTemplate();
	}
}