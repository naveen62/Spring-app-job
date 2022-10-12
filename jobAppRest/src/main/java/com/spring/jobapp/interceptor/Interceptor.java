package com.spring.jobapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.jobapp.exception.TokenInValidException;
import com.spring.jobapp.jwt.JwtTokenUtils;
import com.spring.jobapp.service.UserService;

@Component
public class Interceptor implements HandlerInterceptor {
	
	@Autowired
	private JwtTokenUtils jwt;
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
			if(request.getHeader("Authorization") == null) {
				throw new TokenInValidException("no token found in request");
			}
			if(!request.getHeader("Authorization").startsWith("Bearer ")) {
				throw new TokenInValidException("no Bearer found in auth header");
			}
			
			String token = request.getHeader("Authorization").substring(7);
			
			String username = jwt.getUsernameFromToken(token);
			if(userService.getUser(username) == null) {
				throw new TokenInValidException("Invalid token");
			}
			return true;
	}
	
}
