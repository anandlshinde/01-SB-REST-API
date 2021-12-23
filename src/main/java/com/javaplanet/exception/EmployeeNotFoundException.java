package com.javaplanet.exception;

public class EmployeeNotFoundException extends Exception {

	private static final long serialVersionUID = 4880504470370059984L;

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
