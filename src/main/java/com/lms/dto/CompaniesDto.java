package com.lms.dto;

import org.springframework.stereotype.Component;

import com.lms.entity.status;

import lombok.Data;

@Component
@Data

public class CompaniesDto {

	private String companyId;

	private String companyName;

	private String industry;

	private status status;

}
