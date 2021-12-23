package com.javaplanet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {


	private static final long serialVersionUID = 2386956220886824491L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
