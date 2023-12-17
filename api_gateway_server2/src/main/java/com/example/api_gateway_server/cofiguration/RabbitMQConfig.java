package com.example.api_gateway_server.cofiguration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.Queue;

@Configuration
public class RabbitMQConfig {

	
	private static final Logger log = LoggerFactory.getLogger(RabbitMQConfig.class);

	
//	@Value("${rabbitmq.queue.name}")
//	private String Queue;

	@Value("${activemq.broker.url}")
	private String Exchange;

//	@Value("${rabbitmq.routing.key}")
//	private String routingKey;

	@Bean
	public Queue queue() {
		log.info("=======>1");
		return new ActiveMQQueue("standalone.queue");
	}

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(Exchange);
		return activeMQConnectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		
		JmsTemplate jmsTemplate=new JmsTemplate(activeMQConnectionFactory());
//		jmsTemplate.setDefaultDestinationName(Queue);
		return jmsTemplate;
	}

}

