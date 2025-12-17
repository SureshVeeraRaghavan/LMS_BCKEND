package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.config.ResponseStructure;
import com.lms.entity.Companies;
import com.lms.service.CompaniesService;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
	@Autowired
	CompaniesService companyservice;
	
	@PostMapping("/addcompany")
	public ResponseEntity<ResponseStructure<Companies>> createcompany(@RequestBody Companies companies)
	{
		return companyservice.savecompany(companies);
		
	}

	@GetMapping("/getcompany")
	public ResponseEntity<ResponseStructure<List<Companies>>> getallcompany()
	{
		return companyservice.getallcompanies();
		
	}
	 
	

}
