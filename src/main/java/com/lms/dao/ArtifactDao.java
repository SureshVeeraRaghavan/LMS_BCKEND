package com.lms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.entity.Artifacts;
import com.lms.repository.ArtifactRepository;

@Component
public class ArtifactDao {
	
	@Autowired
	ArtifactRepository artifactRepository;
	
	public Artifacts saveartifacts(Artifacts artifacts)
	{
		return artifactRepository.save(artifacts);
	}
	
	public List<Artifacts> findallartifacts()
	{
		return artifactRepository.findAll();
		
	}

}
