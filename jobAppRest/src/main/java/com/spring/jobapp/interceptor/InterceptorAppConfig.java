package com.spring.jobapp.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorAppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private Interceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		List<String> excludePatternsList = new ArrayList<>();
		excludePatternsList.add("/register");
		excludePatternsList.add("/login");
		excludePatternsList.add("/candidate/resume/{candidate-id}");
		excludePatternsList.add("/candidate/pic/{candidate-id}");
		registry.addInterceptor(interceptor).excludePathPatterns(excludePatternsList);
	}
}
