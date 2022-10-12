package com.spring.jobapp.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jobapp.exception.InCrtValException;
import com.spring.jobapp.jwt.JwtTokenUtils;
import com.spring.jobapp.model.JwtResponse;
import com.spring.jobapp.model.UserModel;
import com.spring.jobapp.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtils jwt;
	
	@PostMapping("/register")
	public JwtResponse handleUserRegister(@RequestBody UserModel user) {
		UserModel newUser = userService.saveUser(user);
		String token = jwt.generateToken(user);
		JwtResponse tokenRes = new JwtResponse(token,user);
		return tokenRes;
	}
	
	@PostMapping("/login")
	public JwtResponse handleUserLogin(@RequestBody UserModel userBody) {
		UserModel user = userService.getUser(userBody.getUsername());
		if(user != null) {
			if(userService.passwordCheck(userBody.getPassword(), user.getPassword())) {
				String token = jwt.generateToken(user);
				return new JwtResponse(token, user);
			} else {
				throw new InCrtValException("incorrect passowrd or username");
			}
		} else {
			throw new InCrtValException("incorrect passowrd or username");
		}
	}
}
