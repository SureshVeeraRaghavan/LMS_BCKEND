package com.lms.entity;



import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.CreatedDate;


@Document(collection = "User")

public class User {

	@Id
	private String id;
	private String firstname;
	private Role role;
	private String lastname;
	private String password;
	private String email;
	private String companyId;
	private String status;
	@CreatedDate
	private Instant CreatedDate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", role=" + role + ", lastname=" + lastname
				+ ", password=" + password + ", email=" + email + ", companyId=" + companyId + ", status=" + status
				+ "]";
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User(String id, String firstname, Role role, String lastname, String password, String email,
			String companyId, String status) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.role = role;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.companyId = companyId;
		this.status = status;
	}




}