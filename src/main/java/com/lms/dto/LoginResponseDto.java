package com.lms.dto;

import com.lms.entity.Role;

import lombok.Data;

@Data
public class LoginResponseDto {
	private String token;
	private String id;
	private Role role;
	private String email;
	private String companyid;
	private String firstname;
	
	

	public LoginResponseDto() {
	}



	public LoginResponseDto(String token, String id,Role role,String email,String companyid,String firstname) {
		super();
		this.token = token;
		this.id = id;
		this.role=role;
		this.email=email;
		this.companyid=companyid;
		this.firstname=firstname;
	}

	

	
}