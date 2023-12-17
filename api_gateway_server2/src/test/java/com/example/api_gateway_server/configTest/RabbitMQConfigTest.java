package com.example.api_gateway_server.configTest;

import javax.naming.Binding;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.Queue;

@Configuration
public class RabbitMQConfigTest {

//	@Value("${rabbitmq.queue.name}")
//	private String Queue;

	@Value("${activemq.broker.url}")
	private String Exchange;

//	@Value("${rabbitmq.routing.key}")
//	private String routingKey;

	@Bean
	public Queue queue() {
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
		return new JmsTemplate(activeMQConnectionFactory());
	}

}

