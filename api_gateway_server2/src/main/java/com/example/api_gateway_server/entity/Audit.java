package com.example.api_gateway_server.entity;

public class Audit {
	private Long id;

	private String httpServletRequest;

	private String httpServletResponse;
	
	private int httpCode;

	public Audit() {
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

	public Audit(String httpServletRequest, String httpServletResponse, int httpCode) {
		super();
		this.httpServletRequest = httpServletRequest;
		this.httpServletResponse = httpServletResponse;
		this.httpCode = httpCode;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	
}
