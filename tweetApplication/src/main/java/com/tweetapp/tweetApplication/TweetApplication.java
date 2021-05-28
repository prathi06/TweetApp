package com.tweetapp.tweetApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.tweetapp.tweetApplication.repo.TweetApplicationRepository;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class TweetApplication  {
	@Autowired
	TweetApplicationRepository tweetRepo;
	@Autowired
	MongoTemplate template;
	
	@Autowired 
	MongoOperations mongoOperations;
		
	public static void main(String[] args) {
		SpringApplication.run(TweetApplication.class, args);
	}

}
