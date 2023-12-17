package com.example.audit_server.exceptionTest;

import org.springframework.http.HttpStatus;

public class BaseResponseTest<T> {
	private HttpStatus status = HttpStatus.OK;

	private String message = "success";

	private T data;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
