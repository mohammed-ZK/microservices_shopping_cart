package com.example.shopping_cart.excptionTest;

public class UserNotAuthenticatedExceptionTest extends Exception {

	private static final long serialVersionUID = -470180507998010368L;

	public UserNotAuthenticatedExceptionTest() {
		super();
	}

	public UserNotAuthenticatedExceptionTest(final String message) {
		super(message);
	}
}
