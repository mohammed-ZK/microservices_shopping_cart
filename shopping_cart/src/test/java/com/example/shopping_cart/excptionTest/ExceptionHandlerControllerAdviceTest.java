package com.example.shopping_cart.excptionTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdviceTest {

	@ExceptionHandler(EmployeeServiceExceptionTest.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponseTest<Void> handleException(final EmployeeServiceExceptionTest exception,
			final HttpServletRequest request) {

		BaseResponseTest<Void> error = new BaseResponseTest<Void>();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

	@ExceptionHandler(ProductExceptionTest.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponseTest<Void> ProductException(final ProductExceptionTest exception,
			final HttpServletRequest request) {

		BaseResponseTest<Void> error = new BaseResponseTest<Void>();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

	@ExceptionHandler(NotGenerateTokenExceptionTest.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponseTest<Void> NotGenerateTokenException(final NotGenerateTokenExceptionTest exception,
			final HttpServletRequest request) {

		BaseResponseTest<Void> error = new BaseResponseTest<Void>();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

	@ExceptionHandler(UserNotExpiredExceptionTest.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponseTest<Void> UserNotExpiredException(final UserNotExpiredExceptionTest exception,
			final HttpServletRequest request) {

		BaseResponseTest<Void> error = new BaseResponseTest<Void>();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

	@ExceptionHandler(UserNotFoundExceptionTest.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponseTest<Void> UserNotFoundException(final UserNotFoundExceptionTest exception,
			final HttpServletRequest request) {

		BaseResponseTest<Void> error = new BaseResponseTest<Void>();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

	@ExceptionHandler(UserNotAuthenticatedExceptionTest.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponseTest<Void> UserNotAuthizException(final UserNotAuthenticatedExceptionTest exception,
			final HttpServletRequest request) {

		BaseResponseTest<Void> error = new BaseResponseTest<Void>();
		error.setMessage("User not Authenticated");
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

	@ExceptionHandler(ErrorInInsertExceptionTest.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponseTest<Void> ErrorInInsertException(final ErrorInInsertExceptionTest exception,
			final HttpServletRequest request) {

		BaseResponseTest<Void> error = new BaseResponseTest<Void>();
		error.setMessage("Not Authenticated");
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponseTest<Void> Exception(final Exception exception, final HttpServletRequest request) {

		BaseResponseTest<Void> error = new BaseResponseTest<Void>();
		error.setMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return error;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> validationHandler(MethodArgumentNotValidException exception) {
		HashMap<String, Object> resObj = new HashMap<String, Object>();
		String errorMsg = "validation is failed!";
		if (exception.getErrorCount() > 0) {
			List<String> errorDetails = new ArrayList<>();
			for (ObjectError error : exception.getBindingResult().getAllErrors()) {
				errorDetails.add(error.getDefaultMessage());
			}

			if (errorDetails.size() > 0)
				errorMsg = errorDetails.get(0);
		}

//		BaseResponseTest<Object> erroreMsg=new BaseResponseTest<>();
//		erroreMsg.setStatus( HttpStatus.BAD_REQUEST);
//		erroreMsg.setMessage(errorMsg);
		resObj.put("status", HttpStatus.BAD_REQUEST.value());
		resObj.put("message", errorMsg);
		return new ResponseEntity<>(resObj, HttpStatus.BAD_REQUEST);
//		return erroreMsg;
	}

}