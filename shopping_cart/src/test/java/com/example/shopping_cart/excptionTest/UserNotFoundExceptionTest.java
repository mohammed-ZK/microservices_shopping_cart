package com.example.shopping_cart.excptionTest;

public class UserNotFoundExceptionTest extends Exception {

	private static final long serialVersionUID = -470180507998010368L;

	public UserNotFoundExceptionTest() {
		super();
	}

	public UserNotFoundExceptionTest(final String message) {
		super(message);
	}
}
