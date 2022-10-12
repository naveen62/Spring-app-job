package com.spring.jobappclient.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController {
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest req) {
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "error/error-404.jsp";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "error/error-500.jsp";
	        } else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
	            return "error/error-400.jsp";
	        }
	    }
	    return "error/error.jsp";
	}
}
