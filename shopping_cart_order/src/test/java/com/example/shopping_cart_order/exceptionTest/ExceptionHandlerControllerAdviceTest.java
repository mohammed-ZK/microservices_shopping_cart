package com.example.shopping_cart_order.exceptionTest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdviceTest {

	@ExceptionHandler(OrderServiceExceptionTest.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponseTest<Void> handleException(final OrderServiceExceptionTest exception,
			final HttpServletRequest request) {

		BaseResponseTest<Void> error = new BaseResponseTest<Void>();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

}
