package com.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.config.ResponseStructure;
import com.lms.dao.AdminDao;
import com.lms.dto.AdminDto;
import com.lms.entity.Admin;
import com.lms.exceptions.Adminalreadyexists;
import com.lms.exceptions.IdNotFoundException;

@Service
public class AdminService {

	@Autowired
	AdminDao admindao;

	@Autowired
	AdminDto admindto;

	public ResponseEntity<ResponseStructure<Admin>> saveadmin(Admin admin) {
		System.out.println(admin);
		if (admin != null) {

			admin = admindao.saveadmin(admin);
			admindto.setId(admin.getId());
			admindto.setAdminname(admin.getAdminname());
			admindto.setAdminpassword(admin.getAdminpassword());
			admindto.setAdminEmail(admin.getAdminEmail());

			ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
			responseStructure.setData(admindto);
			responseStructure.setMessage("admin created successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.CREATED);

		} else {
			throw new Adminalreadyexists("Admin Already exists");
		}

	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findallAdmins() {
		List<Admin> admin = admindao.findalladmins();
		List<AdminDto> admindto = new ArrayList<>();
		if (admin != null) {
			for (Admin a : admin) {
				AdminDto adto = new AdminDto();
				adto.setAdminname(a.getAdminname());
				adto.setAdminEmail(a.getAdminEmail());
				adto.setAdminpassword(a.getAdminpassword());
				adto.setId(a.getId());
				admindto.add(adto);
			}
			ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
			structure.setData(admindto);
			structure.setMessage("Admin founded");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Admin is notfound");
		}

	}

}
