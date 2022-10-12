package com.spring.jobappclient.model;


import org.springframework.http.HttpStatus;

public class ErrorModel {

    private HttpStatus httpStatus;

    private String message;

    private String details;
    
    private boolean error;
    
    
    public ErrorModel() {

	}

	public ErrorModel(HttpStatus httpStatus, String message, String details, boolean error) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.details = details;
        this.error = error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorModel [httpStatus=" + httpStatus + ", message=" + message
				+ ", details=" + details + ", error=" + error + "]";
	}
	
    
}
