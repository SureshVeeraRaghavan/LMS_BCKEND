package com.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.entity.Companies;
import com.lms.repository.CompaniesRepository;

@Component
public class CompaniesDao {
	
	@Autowired
	CompaniesRepository companyrepo;
	
	  public Companies saveCompany(Companies company) {
		return companyrepo.save(company);     
	    }
	  
	  public List<Companies> findallcompanies()
	  {
		return companyrepo.findAll();
	  }

}
