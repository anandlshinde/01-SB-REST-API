package com.javaplanet.exception;

public class FileNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -6095804481626128071L;

	public FileNotFoundException(String message) {
		super(message);
	}
	
	public FileNotFoundException(String message,Throwable throwable) {
		super(message,throwable);
	}

}
