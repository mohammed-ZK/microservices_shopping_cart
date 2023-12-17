package com.bezkoder.springjwt.exception;

public class AuthServiceException extends Exception {

	private static final long serialVersionUID = -470180507998010368L;

	public AuthServiceException() {
		super();
	}

	public AuthServiceException(final String message) {
		super(message);
	}
}
