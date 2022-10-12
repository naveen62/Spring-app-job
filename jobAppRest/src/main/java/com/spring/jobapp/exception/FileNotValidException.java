package com.spring.jobapp.exception;

public class FileNotValidException extends RuntimeException {
	
	public FileNotValidException(String message) {
		super(message);
	}
}
