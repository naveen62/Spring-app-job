package com.spring.jobapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private long userId;
	private String username;
	private String password;
	public UserModel() {
		super();
	}
	public UserModel(long userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
