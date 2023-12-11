package com.example.audit_server.exception;

public class AuditServiceException extends Exception {

	private static final long serialVersionUID = -470180507998010368L;

	public AuditServiceException() {
		super();
	}

	public AuditServiceException(final String message) {
		super(message);
	}
}
