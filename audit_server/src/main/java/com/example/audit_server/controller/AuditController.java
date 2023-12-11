package com.example.audit_server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.audit_server.entity.Audit;
import com.example.audit_server.exception.AuditServiceException;
import com.example.audit_server.exception.BaseResponse;
import com.example.audit_server.service.AuditService;

@RestController
@RequestMapping("/audit")
public class AuditController {

	@Autowired
	private AuditService AuditService;

	@Value("${server.port}")
	private Long port;

	@PostMapping("")
	public ResponseEntity<BaseResponse<Long>> insert(@RequestBody Audit audit)throws AuditServiceException {

		BaseResponse<Long> baseResponse = new BaseResponse<>();
		baseResponse.setMessage(port.toString());
		baseResponse.setData(AuditService.insert(audit));
		return ResponseEntity.ok(baseResponse);

	}

	@GetMapping("/all")
	public ResponseEntity<BaseResponse<List<Audit>>> getAllAudits() throws AuditServiceException{

		BaseResponse<List<Audit>> baseResponse = new BaseResponse<>();
		baseResponse.setMessage(port.toString());
		baseResponse.setData(AuditService.getAllAudits());
		return ResponseEntity.ok(baseResponse);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<BaseResponse<Audit>> getAuditById(@PathVariable Long id) throws AuditServiceException{
		BaseResponse<Audit> baseResponse = new BaseResponse<>();
		baseResponse.setMessage(port.toString());
		baseResponse.setData(AuditService.getAuditById(id));
		return ResponseEntity.ok(baseResponse);
	}
	
}
