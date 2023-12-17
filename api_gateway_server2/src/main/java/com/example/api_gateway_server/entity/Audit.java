package com.example.api_gateway_server.entity;

import java.sql.Timestamp;

public class Audit {
	private Long id;

	private String request;

	private String response;

	private Timestamp time_stamp;
	
	private int http_code;

	public Audit() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Timestamp getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Timestamp time_stamp) {
		this.time_stamp = time_stamp;
	}

	public int getHttp_code() {
		return http_code;
	}

	public void setHttp_code(int http_code) {
		this.http_code = http_code;
	}

	public Audit( String request, String response, Timestamp time_stamp, int http_code) {
		super();
		this.request = request;
		this.response = response;
		this.time_stamp = time_stamp;
		this.http_code = http_code;
	}

	@Override
	public String toString() {
		return "Audit{" + "httpServletRequest='" + request + '\'' + ", httpServletResponse="
				+ response + '\'' + ", httpCode=" + http_code + '}';
	}

}
