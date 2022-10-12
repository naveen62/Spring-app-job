package com.spring.jobapp.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jobapp.exception.UserExistsException;
import com.spring.jobapp.model.UserModel;
import com.spring.jobapp.repo.IUserRepo;

@Service
public class UserService {
	
	@Autowired
	private IUserRepo userRepo;
	
	public UserModel saveUser(UserModel user) {
		if(this.getUser(user.getUsername()) != null) {
			throw new UserExistsException("Username already exsits");
		}
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedPassword);
		return userRepo.save(user);
	}
	
	public boolean passwordCheck(String password, String hashPassword) {
		if(BCrypt.checkpw(password, hashPassword)) {
			return true;
		} 
		return false;
	}
	
	public UserModel getUser(String username) {
		return userRepo.findByUsername(username);
	}
}
