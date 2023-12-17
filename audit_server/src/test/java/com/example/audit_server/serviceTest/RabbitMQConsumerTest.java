package com.example.audit_server.serviceTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.audit_server.entity.Audit;
import com.example.audit_server.exception.AuditServiceException;
import com.example.audit_server.service.AuditService;
import com.example.audit_server.service.RabbitMQConsumer;

public class RabbitMQConsumerTest {

    @Mock
    private AuditService auditService;

    @InjectMocks
    private RabbitMQConsumer rabbitMQConsumer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsumer() throws AuditServiceException {
        // Arrange
        String message = "Request,Response,200";

        // Act
//        rabbitMQConsumer.counsumer(message);

        // Assert
        // Verify that the auditService.insert method is called with the expected Audit object
        verify(auditService, times(1)).insert(any(Audit.class));
    }
}
