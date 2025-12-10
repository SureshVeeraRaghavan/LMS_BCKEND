package com.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.config.ResponseStructure;
import com.lms.dao.ArtifactDao;
import com.lms.dto.ArtifactDto;
import com.lms.entity.Artifacts;
import com.lms.exceptions.ArtifactsIdnotfoundException;

@Service
public class ArtifactService {

	@Autowired
	ArtifactDao artifactdao;

	@Autowired
	ArtifactDto artifactdto;

	public ResponseEntity<ResponseStructure<Artifacts>> saveartifact(Artifacts artifacts) {

		if (artifacts != null) {
			artifacts = artifactdao.saveartifacts(artifacts);
			artifactdto.setArtifactId(artifacts.getArtifactId());
			artifactdto.setCourseId(artifacts.getCourseId());
			artifactdto.setType(artifacts.getType());
			artifactdto.setTitle(artifacts.getTitle());
			artifactdto.setDescription(artifacts.getDescription());
			artifactdto.setFileFilename(artifacts.getFileFilename());
			artifactdto.setFileMimeType(artifacts.getFileMimeType());
			artifactdto.setFileStorageKey(artifacts.getFileStorageKey());
			artifactdto.setFileSizeBytes(artifacts.getFileSizeBytes());
			artifactdto.setFileChecksum(artifacts.getFileChecksum());
			ResponseStructure<Artifacts> responseStructure = new ResponseStructure<Artifacts>();
			responseStructure.setMessage("artifacts detail created successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(artifactdto);
			return new ResponseEntity<ResponseStructure<Artifacts>>(responseStructure, HttpStatus.CREATED);

		}
		return null;

	}

	public ResponseEntity<ResponseStructure<List<Artifacts>>> findallartifacts() {
		List<Artifacts> artifact = artifactdao.findallartifacts();
		List<ArtifactDto> artifactdto = new ArrayList<>();
		if (artifact != null) {
			for (Artifacts a : artifact) {
				ArtifactDto adto = new ArtifactDto();
				adto.setArtifactId(a.getArtifactId());
				adto.setCourseId(a.getCourseId());
				adto.setDescription(a.getDescription());
				adto.setId(a.getId());
				adto.setType(a.getType());
				adto.setTitle(a.getTitle());
				adto.setDescription(a.getDescription());
				adto.setFileFilename(a.getFileFilename());
				adto.setFileMimeType(a.getFileMimeType());
				adto.setFileSizeBytes(a.getFileSizeBytes());
				adto.setFileStorageProvider(a.getFileStorageProvider());
				adto.setFileStorageBucket(a.getFileStorageBucket());
				adto.setFileStorageKey(a.getFileStorageKey());
				adto.setUploadedBy(a.getUploadedBy());
				adto.setUploadedAt(a.getUploadedAt());
				adto.setValidationStatus(a.getValidationStatus());
				adto.setValidationReason(a.getValidationReason());

				artifactdto.add(adto);
			}
			ResponseStructure<List<Artifacts>> structure = new ResponseStructure<>();
			structure.setData(artifactdto);
			structure.setMessage("course founded");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Artifacts>>>(structure, HttpStatus.FOUND);
		} else {
			throw new ArtifactsIdnotfoundException("couse not found");
		}

	}

}
