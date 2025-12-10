package com.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.entity.Admin;
import com.lms.repository.AdminRepository;

@Component
public class AdminDao {

	@Autowired
	AdminRepository adminrepository;

	public Admin saveadmin(Admin admin) {
		return adminrepository.save(admin);

	}

	public List<Admin> findalladmins() {
		return adminrepository.findAll();

	}

}
