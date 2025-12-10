package com.lms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.entity.Admin;

public interface AdminRepository  extends MongoRepository<Admin, String>{

}
