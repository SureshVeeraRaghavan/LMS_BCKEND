package com.lms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
//	
//	    @Query(value = "{ 'username': ?0 }", fields = "{ 'username' : 1, '_Id' : 0 }")
//	    Optional<Users> findUsernameByUsername(String username);
//	    @Query(value = "{ 'username': ?0 }", fields = "{ 'username' : 1, '_id' : 0 }")
//	    Optional<Users> findUsernameByUsername(String username);
//	   public User findByUsername(String username);
	   public User email(String email);




}
