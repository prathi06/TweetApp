package com.tweetapp.tweetApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class TweetApplicationConfiguration {

	
	 public @Bean MongoClient mongoClient() {
	      return MongoClients.create("mongodb://mongo_db:27017");
	  }

	  public @Bean MongoTemplate mongoTemplate() {
	      return new MongoTemplate(mongoClient(), "TweetAppdb");
	  }
	
	  @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("com.tweetapp.tweetApplication.controller"))              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
	  
}
