package com.spring.jobapp.restController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.jobapp.exception.FileNotValidException;
import com.spring.jobapp.exception.InCrtValException;
import com.spring.jobapp.exception.ItemNotFoundException;
import com.spring.jobapp.exception.TokenInValidException;
import com.spring.jobapp.model.ErrorModel;

@RestControllerAdvice
public class ExceptionController {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorModel handleValidation(MethodArgumentNotValidException e) {
		return new ErrorModel(HttpStatus.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage(), e.getBindingResult().toString(), true);
	}
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ItemNotFoundException.class)
	public ErrorModel handleItemNotFoundException(Exception ex) {
		return new ErrorModel(HttpStatus.NOT_FOUND, ex.getMessage(), null, true);
	}
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FileNotValidException.class)
	public ErrorModel handleFileNotValidEx(Exception ex) {
		return new ErrorModel(HttpStatus.BAD_REQUEST, ex.getMessage(), null, true);
	}
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestPartException.class)
	public ErrorModel handleMultipartException(MissingServletRequestPartException ex) {
		return new ErrorModel(HttpStatus.BAD_REQUEST, ex.getMessage(), null, true);
	}
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ErrorModel handleBindException(BindException ex) {
		List<String> errListFields = ex.getFieldErrors().stream().map(item -> item.getField()).collect(Collectors.toList());
		String message = "Provide candidate "+ errListFields.toString();
		return new ErrorModel(HttpStatus.BAD_REQUEST, message, ex.getBindingResult().toString(), true);
	}
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(TokenInValidException.class)
	public ErrorModel handleTokenInValidException(Exception ex) {
		return new ErrorModel(HttpStatus.UNAUTHORIZED, ex.getMessage(), null, true);
	}
	
	@ExceptionHandler(InCrtValException.class)
	public ErrorModel handleIncrtVal(InCrtValException ex) {
		return new ErrorModel(HttpStatus.BAD_REQUEST, ex.getMessage(), null, true);
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ErrorModel handleGeneralException(Exception ex) {
		return new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null, true);
	}

}
