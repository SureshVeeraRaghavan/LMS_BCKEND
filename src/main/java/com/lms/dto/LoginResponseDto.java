package com.lms.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
	private String token;
	private String id;
	private String user;
	private String role;
	private String useremail;

	public LoginResponseDto() {
	}

	public LoginResponseDto(String token, String id, String user, String role, String useremail) {
		this.token = token;
		this.id = id;
		this.user = user;
		this.role = role;
		this.useremail = useremail;
	}
}