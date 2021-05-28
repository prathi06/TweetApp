package com.tweetapp.tweetApplication.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.tweetApplication.model.User;

@Repository
public interface TweetApplicationRepository extends MongoRepository<User,String> {

	List<User> findByFirstName(final String firstName);
	
	User findByEmail(String email);
}
