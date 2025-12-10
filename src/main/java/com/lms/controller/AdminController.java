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
import com.lms.entity.Admin;
import com.lms.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
	
	@Autowired
	AdminService adminservice;
	
	@PostMapping("/saveadmin")
	public ResponseEntity<ResponseStructure<Admin>> Saveadmin(@RequestBody Admin admin)
	{
		return adminservice.saveadmin(admin);
		
	}
	@GetMapping("/getalladmin")
	public ResponseEntity<ResponseStructure<List<Admin>>> findalladmin()
	{
		return adminservice.findallAdmins();
		
	}

}
