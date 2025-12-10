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
import com.lms.dto.LoginResponseDto;
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
	private jwtutil jwt;

	public ResponseEntity<ResponseStructure<Users>> saveuser(Users user) {

		Users useremail = userdao.finduserbyemail(user.getUsermail());
		System.out.println(useremail);
		if (useremail == null) {
			user = userdao.saveuser(user);
			userdto.setUsername(user.getUsername());
			userdto.setPassword(user.getPassword());
			userdto.setUsermail(user.getUsermail());
			userdto.setId(user.getId());
			ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();
			responseStructure.setMessage("user created successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(userdto);
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new UserAlreadyExistsException("user with this email already exists");
		}

	}

	public ResponseEntity<ResponseStructure<Users>> loginuser(String name, String password) {
		Users user = userdao.findUserByName(name);

		System.out.println(user + "success");

		if (user == null) {
			ResponseStructure<Users> structure = new ResponseStructure<Users>();
			structure.setMessage("user not found");
			structure.setStatus(HttpStatus.UNAUTHORIZED.value());
			return new ResponseEntity<>(structure, HttpStatus.UNAUTHORIZED);
		}
		String password1 = user.getPassword();
		if (!password.equals(password1)) {
			ResponseStructure<Users> structure = new ResponseStructure<Users>();
			structure.setMessage("Invalid password");
			structure.setStatus(HttpStatus.UNAUTHORIZED.value());
			structure.setData(null);
			return new ResponseEntity<>(structure, HttpStatus.UNAUTHORIZED);
		}
		if (user != null) {
			String token = jwt.generetatoken(name);

			// Create login response DTO with all user details
			LoginResponseDto loginResponse = new LoginResponseDto();
			loginResponse.setToken(token);
			loginResponse.setId(user.getId());
			loginResponse.setUser(user.getUsername());
			loginResponse.setUseremail(user.getUsermail());
			loginResponse.setRole(user.getRole()); // You can get this from user entity if you have role field

			ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();
			responseStructure.setMessage("user successfully logined");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(loginResponse);
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.OK);
		} else {
			throw new InvalidLoginException("Invalid username or password");
		}

	}

	public ResponseEntity<ResponseStructure<List<Users>>> findalltheusers() {
		List<Users> users = userdao.findalluser();
		List<UserDto> userdto = new ArrayList<>();
		if (users != null) {
			for (Users s : users) {
				UserDto userdt = new UserDto();
				userdt.setUsername(s.getUsername());
				userdt.setPassword(s.getPassword());
				userdt.setId(s.getId());
				userdto.add(userdt);

			}
			ResponseStructure<List<Users>> structure = new ResponseStructure<>();
			structure.setData(userdto);
			structure.setMessage(" users founded ");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Users>>>(structure, HttpStatus.FOUND);
		} else {
			throw new UserIdnotFoundException("users not found ");
		}

	}

}
