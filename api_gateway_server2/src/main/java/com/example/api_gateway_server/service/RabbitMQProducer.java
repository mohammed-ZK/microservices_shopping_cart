package com.example.api_gateway_server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.example.api_gateway_server.entity.Audit;

import jakarta.jms.Queue;

@Service
public class RabbitMQProducer {

	private static final Logger log = LoggerFactory.getLogger(RabbitMQProducer.class);

//	@Value("${rabbitmq.exchange.name}")
//	private String Exchange;
//
//	@Value("${rabbitmq.routing.key}")
//	private String routingKey;

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Queue queue;

//	public RabbitMQProducerTest(RabbitTemplate rabbitTemplate) {
//		super();
//		this.rabbitTemplate = rabbitTemplate;
//	}

	public void sendMessage(String order) {
//		log.info(order.getHttpServletRequest() + "====>" + order.getHttpServletResponse());
//		String message = order.getHttpServletRequest() + "&&" + order.getHttpServletResponse()+"&&"+order.getHttpCode();
//		log.info(message);
		jmsTemplate.convertAndSend(queue,order);
	}

}

