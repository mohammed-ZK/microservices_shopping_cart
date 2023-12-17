package com.example.audit_server.entityTest;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AuditTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String httpServletRequest;

	private String httpServletResponse;

	private Timestamp date;
	
	private int httpStatus;

	public AuditTest() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHttpServletRequest() {
		return httpServletRequest;
	}

	public void setHttpServletRequest(String httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	public String getHttpServletResponse() {
		return httpServletResponse;
	}

	public void setHttpServletResponse(String httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}
