package com.kundan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
		return new ResponseEntity<ErrorMessage>(
				new ErrorMessage(
						resourceNotFoundException.getMessage(),
						"Resource Not Found",
						"Data Not Found For Given Id",
						"Student"),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorMessage> handleRuntimeException(){
		return null;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleException(){
		return null;
	}

}
