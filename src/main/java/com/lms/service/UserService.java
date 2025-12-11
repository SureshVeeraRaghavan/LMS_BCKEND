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
import com.lms.exceptions.UserIdnotFoundException;
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
		System.out.println(useremail +"h");
		if (useremail== null) {
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

	public ResponseEntity<ResponseStructure<Users>> loginuser(String name, String password) {
//		User user = userdao.findUserByName(name);

//		System.out.println(user + "success");

		if (name!= null) {
			ResponseStructure<Users> structure = new ResponseStructure<Users>();
			structure.setMessage("user not found");
			structure.setStatus(HttpStatus.UNAUTHORIZED.value());
			return new ResponseEntity<>(structure, HttpStatus.UNAUTHORIZED);
		}
//		String password1 = user.getPassword();
//		if (!password.equals(password1)) {
//			ResponseStructure<Users> structure = new ResponseStructure<Users>();
//			structure.setMessage("Invalid password");
//			structure.setStatus(HttpStatus.UNAUTHORIZED.value());
//			structure.setData(null);
//			return new ResponseEntity<>(structure, HttpStatus.UNAUTHORIZED);
//		}
		if (name != null) {
			String token = jwt.generetatoken(name);

			// Create login response DTO with all user details
			LoginResponseDto loginResponse = new LoginResponseDto();
			loginResponse.setToken(token);
			 // You can get this from user entity if you have role field

			ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();
			responseStructure.setMessage("user successfully logined");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(loginResponse);
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.OK);
		} else {
			throw new InvalidLoginException("Invalid username or password");
		}

	}

	

}
