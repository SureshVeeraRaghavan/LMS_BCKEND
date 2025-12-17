package com.lms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.entity.Companies;

public interface CompaniesRepository extends MongoRepository<Companies, String> {

}
