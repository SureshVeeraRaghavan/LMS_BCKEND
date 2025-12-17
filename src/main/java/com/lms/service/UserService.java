package com.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.config.ResponseStructure;
import com.lms.dao.UserDao;
import com.lms.dto.UserDto;
import com.lms.dto.UsersDto;
import com.lms.dto.LoginResponseDto;

import com.lms.entity.User;
import com.lms.entity.Users;
import com.lms.exceptions.UserAlreadyExistsException;

import com.lms.exceptions.InvalidLoginException;
import com.lms.utils.jwtutil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	
	@Autowired
	UserDao userdao;
	
	@Autowired
	UserDto userdto;
	
	@Autowired
	UsersDto usersdto;
	
	@Autowired
	private jwtutil jwt;
	public ResponseEntity<ResponseStructure<Users>> saveuser(User user) {
		User useremail = userdao.finduserbyemail(user.getEmail());
		System.out.println(useremail + "h");
		if (useremail == null) {
			user = userdao.saveuser(user);
			usersdto.setFirstname(user.getFirstname());
			usersdto.setLastname(user.getLastname());
			usersdto.setPassword(user.getPassword());
			usersdto.setEmail(user.getEmail());
			usersdto.setRole(user.getRole());
			usersdto.setStatus(user.getStatus());
			usersdto.setCompanyId(user.getCompanyId());
			ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();
			responseStructure.setMessage("user created successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(usersdto);
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new UserAlreadyExistsException("user with this email already exists");
		}
	}
	public ResponseEntity<ResponseStructure<User>> loginuser(String email, String password) {
		User user = userdao.finduserbyemail(email);
		if (user == null) {
			ResponseStructure<User>structure = new ResponseStructure<User>();
			structure.setMessage("user not found");
			structure.setStatus(HttpStatus.UNAUTHORIZED.value());
			return new ResponseEntity<>(structure, HttpStatus.UNAUTHORIZED);
		}
		String password1 = user.getPassword();
		if (!password.equals(password1)) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setMessage("Invalid password");
			structure.setStatus(HttpStatus.UNAUTHORIZED.value());
			structure.setData(null);
			return new ResponseEntity<>(structure, HttpStatus.UNAUTHORIZED);
		}
		if (email != null) {
			String token = jwt.generetatoken(email);
//			 Create login response DTO with all user details
			LoginResponseDto loginResponse = new LoginResponseDto();
			loginResponse.setToken(token);
			loginResponse.setRole(user.getRole());
			loginResponse.setEmail(user.getEmail());
			loginResponse.setCompanyid(user.getCompanyId());
			loginResponse.setId(user.getId());
			loginResponse.setFirstname(user.getFirstname());
//			  You can get this from user entity if you have role field
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setMessage("user successfully logined");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(loginResponse);
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		} else {
			throw new InvalidLoginException("Invalid username or password");
		}

	}
	public ResponseEntity<ResponseStructure<List<User>>> findallusers() {
		List<User> users = userdao.findalluser();
		if (users != null && !users.isEmpty()) {
			ResponseStructure<List<User>> structure = new ResponseStructure<>();
			structure.setMessage("Users found");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(users);
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
		}
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setData(new ArrayList<>());
		structure.setMessage("No Users found");
		structure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
	}
}
