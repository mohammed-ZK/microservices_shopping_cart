package com.example.audit_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.audit_server.entity.Audit;
import com.example.audit_server.repository.AuditRepository;

@Service
public class AuditService {

	@Autowired
	private AuditRepository auditRepository;
	
	public List<Audit> getAllAudits() {
		return auditRepository.findAll();
	}

	public Audit getAuditById(Long id) {
		return auditRepository.findById(id).get();
	}

	public Long insert(Audit audit) {
		return auditRepository.save(audit).getId();
	}

}
