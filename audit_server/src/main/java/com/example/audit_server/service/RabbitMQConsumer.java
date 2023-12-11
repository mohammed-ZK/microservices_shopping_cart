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

@Service
public class RabbitMQConsumer {

	private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@Autowired
	AuditService auditService;

//	@RabbitListener(queues = "${rabbitmq.queue.name}")
	@JmsListener(destination = "standalone.queue")
	public void counsumer(String message) throws AuditServiceException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Audit audit = new Audit();

		String[] arrOfStr = message.split(",", 3);

		for (String a : arrOfStr)
			log.info("=====>" + a);

		audit.setHttpServletRequest(arrOfStr[0]);
		audit.setHttpServletResponse(arrOfStr[1]);
		audit.setHttpStatus(Integer.parseInt(arrOfStr[2]));
		audit.setDate(timestamp);

		log.info("======>" + audit.getHttpServletRequest());
		log.info("======>" + audit.getHttpServletResponse());
		log.info("======>" + audit.getHttpStatus());
		log.info("======>" + audit.getDate());

		auditService.insert(audit);

	}

}