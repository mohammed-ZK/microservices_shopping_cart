package com.example.audit_server.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.audit_server.controller.AuditController;
import com.example.audit_server.entity.Audit;
import com.example.audit_server.service.AuditService;

@WebMvcTest(AuditController.class)
public class AuditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditService auditService;

    @InjectMocks
    private AuditController auditController;

    @Test
    public void testInsert() throws Exception {
        // Arrange
    	Timestamp timestamp= new Timestamp(2344232L);
        Audit audit = new Audit( "qq1","qq1",timestamp,200);
        when(auditService.insert(any(Audit.class))).thenReturn(1L);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/audit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"property1\":\"value1\",\"property2\":\"value2\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("2020"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(1L));
    }

    @Test
    public void testGetAllAudits() throws Exception {
        // Arrange
    	Timestamp timestamp= new Timestamp(2344232L);
        List<Audit> audits = Arrays.asList(new Audit("qq1","qq1",timestamp,200));
        when(auditService.getAllAudits()).thenReturn(audits);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/audit/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("2020"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray());
    }

    @Test
    public void testGetAuditById() throws Exception {
        // Arrange
    	Timestamp timestamp= new Timestamp(2344232L);
        Audit audit = new Audit( "qq1","qq1",timestamp,200);
        when(auditService.getAuditById(anyLong())).thenReturn(audit);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/audit/get/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("2020"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isMap());
    }
}
