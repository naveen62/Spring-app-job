package com.spring.jobapp.exception;

public class UserExistsException extends RuntimeException {
	public UserExistsException(String message) {
		super(message);
	}
}
