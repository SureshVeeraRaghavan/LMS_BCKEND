package com.lms.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {

	@Id
	private String Id;
	private String username;
	private String role;
	private String Password;
	private String useremail;

	public String getUsermail() {
		return useremail;
	}

	public void setUsermail(String usermail) {
		this.useremail = usermail;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	@Override
	public String toString() {
		return "Users [Id=" + Id + ", username=" + username + ", role=" + role + ", Password=" + Password
				+ ", useremail=" + useremail + "]";
	}

}
