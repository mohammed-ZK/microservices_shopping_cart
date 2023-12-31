package com.example.audit_server.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.example.audit_server.entity.Audit;
import com.example.audit_server.exception.AuditServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitMQConsumer {

	private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@Autowired
	AuditService auditService;

//	@RabbitListener(queues = "${rabbitmq.queue.name}")
	@JmsListener(destination = "standalone.queue")
	public void counsumer(String message) throws AuditServiceException {
//		String jsonString = ((TextMessage) message).getText();
		 ObjectMapper objectMapper = new ObjectMapper();
         try {
			Audit audit = objectMapper.readValue(message, Audit.class);
	         log.info("Code as " +message);
	         log.info("Code as " +audit.getHttp_code());
	         auditService.insert(audit);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		Audit audit = new Audit();
//
//		String[] arrOfStr = message.split("&&", 3);
//
//		for (String a : arrOfStr)
//			log.info("=====>" + a);
//
//		audit.setRequest(arrOfStr[0]);
//		audit.setResponse(arrOfStr[1]);
//		audit.setHttp_code(Integer.parseInt(arrOfStr[2]));
//		audit.setTime_stamp(timestamp);
//
////		log.info("======>" + audit.getHttpServletRequest());
////		log.info("======>" + audit.getHttpServletResponse());
////		log.info("======>" + audit.getHttpStatus());
////		log.info("======>" + audit.getDate());
//
//		auditService.insert(audit);

	}

}