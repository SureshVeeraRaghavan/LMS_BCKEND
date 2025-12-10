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
import com.lms.entity.Artifacts;
import com.lms.service.ArtifactService;

@RestController
@RequestMapping("/artifact")
public class ArtifactController {
	
	@Autowired
    ArtifactService artifactservice;
	
	@PostMapping("/savearifact")
	public ResponseEntity<ResponseStructure<Artifacts>> saveartifacts(@RequestBody Artifacts artifacts)
	{
		return artifactservice.saveartifact(artifacts);
		
	}
	@GetMapping("/findallartifact")
	public ResponseEntity<ResponseStructure<List<Artifacts>>> findallartifacts()
	{
		return artifactservice.findallartifacts();
		
	}

}
