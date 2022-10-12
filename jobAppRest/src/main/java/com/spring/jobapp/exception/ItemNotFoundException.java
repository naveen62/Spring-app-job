package com.spring.jobapp.exception;

public class ItemNotFoundException extends RuntimeException {
	
	public ItemNotFoundException(String message) {
		super(message);
	}
}
