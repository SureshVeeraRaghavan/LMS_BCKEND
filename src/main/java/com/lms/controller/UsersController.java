package com.lms.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lms.config.ResponseStructure;
import com.lms.entity.User;
import com.lms.entity.Users;
import com.lms.repository.UserRepository;
import com.lms.service.UserService;
@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserService userservice;

	@Autowired
	UserRepository userrepo;
   
	
	@PostMapping("/userdetails")
	public ResponseEntity<ResponseStructure<Users>> saveusers(@RequestBody User user) {

		return userservice.saveuser(user);

	}

	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<User>> loginuser(@RequestBody User u) {
		String name = u.getEmail();
		String password=u.getPassword();
		return userservice.loginuser(name, password);
		
	}

	@GetMapping("/findalltheusers")
	public ResponseEntity<ResponseStructure<List<User>>> findtheuser() {
		return userservice.findallusers();

	}

}