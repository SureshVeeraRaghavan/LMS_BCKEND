package com.lms.dto;


import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserDto {
	
	
	private String Id;
	private String username;
	private String Password;
	private String usermail;
	private String role;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getUsermail() {
		return usermail;
	}
	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}
	

}

