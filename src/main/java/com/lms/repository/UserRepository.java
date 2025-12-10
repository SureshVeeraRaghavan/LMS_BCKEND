package com.lms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
//	
//	    @Query(value = "{ 'username': ?0 }", fields = "{ 'username' : 1, '_Id' : 0 }")
//	    Optional<Users> findUsernameByUsername(String username);
//	    @Query(value = "{ 'username': ?0 }", fields = "{ 'username' : 1, '_id' : 0 }")
//	    Optional<Users> findUsernameByUsername(String username);
	   public Users findByUsername(String username);
	   public Users useremail(String email);




}
