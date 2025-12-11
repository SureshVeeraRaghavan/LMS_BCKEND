package com.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.entity.User;

import com.lms.repository.UserRepository;

@Component
public class UserDao {

	@Autowired
	UserRepository users;

	public User saveuser(User user) {
		return users.save(user);
	}

	// find the user by name
//	public User findUserByName(String name) {
//		User userdetail = users.findByUsername(name);
//
//		if (userdetail != null) {
//			return userdetail;
//		} else {
//			return null;
//		}
//
//	}
//
	public User finduserbyemail(String email) {
		User useremail = users.email(email);
		System.out.println(useremail);
		if (useremail != null) {
			return useremail;
		}
		return null;

	}

	// find the user details
	public List<User> findalluser() {
		return users.findAll();

	}

}
