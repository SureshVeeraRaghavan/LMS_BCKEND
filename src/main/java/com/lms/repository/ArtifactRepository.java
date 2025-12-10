package com.lms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.Artifacts;
@Repository
public interface ArtifactRepository extends MongoRepository<Artifacts, String>{

}
