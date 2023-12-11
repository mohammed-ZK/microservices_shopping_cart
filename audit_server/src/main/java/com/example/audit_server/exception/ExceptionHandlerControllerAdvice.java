package com.example.audit_server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(AuditServiceException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponse<Void> handleException(final AuditServiceException exception,
			final HttpServletRequest request) {

		BaseResponse<Void> error = new BaseResponse<Void>();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

}
