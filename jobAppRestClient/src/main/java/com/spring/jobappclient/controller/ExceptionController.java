package com.spring.jobappclient.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jobappclient.model.ErrorModel;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(HttpClientErrorException.class)
	public String handleAuthError(HttpClientErrorException ex,RedirectAttributes ra) throws JsonMappingException, JsonProcessingException {
		ErrorModel error = new ObjectMapper().findAndRegisterModules().readValue(ex.getResponseBodyAsString(), ErrorModel.class);
		ra.addFlashAttribute("auth-err","invalid authorization");
		return "redirect:/login";
	}
}
