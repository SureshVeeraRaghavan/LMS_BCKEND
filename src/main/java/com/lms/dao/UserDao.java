package com.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.entity.Users;
import com.lms.repository.UserRepository;

@Component
public class UserDao {

	@Autowired
	UserRepository users;

	public Users saveuser(Users user) {
		return users.save(user);
	}

	// find the user by name
	public Users findUserByName(String name) {
		Users userdetail = users.findByUsername(name);

		if (userdetail != null) {
			return userdetail;
		} else {
			return null;
		}

	}

	public Users finduserbyemail(String email) {
		Users useremail = users.useremail(email);
		if (useremail != null) {
			return useremail;
		}
		return null;

	}

	// find the user details
	public List<Users> findalluser() {
		return users.findAll();

	}

}
