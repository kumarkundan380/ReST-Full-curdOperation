package com.kundan.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1459503675239201644L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
