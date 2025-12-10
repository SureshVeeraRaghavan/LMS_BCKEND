package com.lms.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "admin")
@Data
public class Admin {

	
	@Id
	private String Id;
	private String adminname;
	private String adminEmail;
//	private String adminPassword;
	private String adminpassword;
}
