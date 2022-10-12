package com.spring.jobapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jobapp.model.UserModel;

@Repository
public interface IUserRepo extends JpaRepository<UserModel, Long> {
	public UserModel findByUsername(String name);
}
