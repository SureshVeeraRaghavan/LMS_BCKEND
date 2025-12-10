package com.lms.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "artifacts")
@Data
public class Artifacts {
	
	   @Id
	   private String id;

	    private String artifactId;
	    private String courseId;
	    private String type;
	    private String title;
	    private String description;
	    private String fileFilename;
	    private String fileMimeType;
	    private long fileSizeBytes;
	    private String fileStorageProvider;
	    private String fileStorageBucket;
	    private String fileStorageKey;
	    private String fileChecksum;
	    private String uploadedBy;
	    private Instant uploadedAt;
	    private String validationStatus;
	    private String validationReason;

}
