package com.example.audit_server.exceptionTest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdviceTest {

	@ExceptionHandler(AuditServiceExceptionTest.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponseTest<Void> handleException(final AuditServiceExceptionTest exception,
			final HttpServletRequest request) {

		BaseResponseTest<Void> error = new BaseResponseTest<Void>();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

}
