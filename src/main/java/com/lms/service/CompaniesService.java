package com.lms.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.lms.config.ResponseStructure;
import com.lms.dao.CompaniesDao;
import com.lms.dto.CompaniesDto;
import com.lms.entity.Companies;
import com.lms.exceptions.UserAlreadyExistsException;

@Service
public class CompaniesService {

	@Autowired
	CompaniesDao company;

	@Autowired
	CompaniesDto companydto;

	public ResponseEntity<ResponseStructure<Companies>> savecompany(Companies compani) {

		if (compani != null) {
			compani = company.saveCompany(compani);
			companydto.setCompanyId(compani.getCompanyId());
			companydto.setCompanyName(compani.getCompanyName());
			companydto.setIndustry(compani.getIndustry());
			companydto.setStatus(compani.getStatus());
			ResponseStructure<Companies> responseStructure = new ResponseStructure<Companies>();
			responseStructure.setMessage("Company  created successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(companydto);
			return new ResponseEntity<ResponseStructure<Companies>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new UserAlreadyExistsException("Company already exists");
		}

	}

	public ResponseEntity<ResponseStructure<List<Companies>>> getallcompanies() {
		List<Companies> getcompanies = company.findallcompanies();
		if (getcompanies != null && !getcompanies.isEmpty()) {
			List<CompaniesDto> dtoList = new ArrayList<>();
			for (Companies c : getcompanies) {
				CompaniesDto compamiesDto = new CompaniesDto();
				compamiesDto.setCompanyId(c.getCompanyId());
				compamiesDto.setCompanyName(c.getCompanyName());
				compamiesDto.setIndustry(c.getIndustry());
				compamiesDto.setStatus(c.getStatus());
				dtoList.add(compamiesDto);
			}
			ResponseStructure<List<Companies>> structure = new ResponseStructure<>();
			structure.setData(dtoList);
			structure.setMessage("Company found for user");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Companies>>>(structure, HttpStatus.OK);
		} else {
			// Return empty list instead of throwing exception
			ResponseStructure<List<Companies>> structure = new ResponseStructure<>();
			structure.setData(new ArrayList<>());
			structure.setMessage("No company found for this user");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Companies>>>(structure, HttpStatus.OK);
		}
	}

}
