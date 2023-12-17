package com.example.audit_server.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.audit_server.entity.Audit;
import com.example.audit_server.repository.AuditRepository;
import com.example.audit_server.service.AuditService;

public class AuditServiceTest {

    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private AuditService auditService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAudits() {
        // Arrange
    	Timestamp timestamp= new Timestamp(2344232L);
        Audit audit1 = new Audit( "qq1","qq1",timestamp,200);
        Audit audit2 = new Audit( "qq2","qq2",timestamp,200);
        List<Audit> expectedAudits = Arrays.asList(audit1, audit2);

        when(auditRepository.findAll()).thenReturn(expectedAudits);

        // Act
        List<Audit> actualAudits = auditService.getAllAudits();

        // Assert
        assertThat(actualAudits).isEqualTo(expectedAudits);
        verify(auditRepository, times(1)).findAll();
    }

    @Test
    public void testGetAuditById() {
        // Arrange
        Long auditId = 1L;
    	Timestamp timestamp= new Timestamp(2344232L);
        Audit expectedAudit = new Audit( "qq1","qq1",timestamp,200);

        when(auditRepository.findById(auditId)).thenReturn(Optional.of(expectedAudit));

        // Act
        Audit actualAudit = auditService.getAuditById(auditId);

        // Assert
        assertThat(actualAudit).isEqualTo(expectedAudit);
        verify(auditRepository, times(1)).findById(auditId);
    }

    @Test
    public void testInsertAudit() {
        // Arrange

    	Timestamp timestamp= new Timestamp(2344232L);
        Audit auditToInsert = new Audit( "qq1","qq1",timestamp,200);
        when(auditRepository.save(any(Audit.class))).thenAnswer(invocation -> {
            Audit savedAudit = invocation.getArgument(0);
            savedAudit.setId(1L); // Simulate saving the audit and getting an ID
            return savedAudit;
        });

        // Act
        Long savedAuditId = auditService.insert(auditToInsert);

        // Assert
        assertThat(savedAuditId).isNotNull();
        verify(auditRepository, times(1)).save(any(Audit.class));
    }
}
