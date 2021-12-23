package com.javaplanet.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaplanet.dto.ErrorDetailsDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	Date dt=new Date();
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<?> employeeNotFoundException(EmployeeNotFoundException exception,WebRequest request){
		ErrorDetailsDto errorDetailsDto=new ErrorDetailsDto(dt, exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetailsDto,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception,WebRequest request){
		ErrorDetailsDto errorDetailsDto=new ErrorDetailsDto(dt, exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetailsDto,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception exception,WebRequest request) {
		ErrorDetailsDto errorDetailsDto=new ErrorDetailsDto(dt, exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetailsDto,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetailsDto errorDetailsDto=new ErrorDetailsDto(dt, "Validation failed", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(errorDetailsDto,HttpStatus.BAD_REQUEST);
	}
}
