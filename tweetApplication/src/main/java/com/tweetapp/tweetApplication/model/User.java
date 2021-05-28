package com.tweetapp.tweetApplication.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document("user")
@JsonInclude(Include.NON_NULL)
public class User {
	
	public User() {}
	
	public User(String firstName, String lastName, String gender, String email, String password,
			String contactNumber, List<Tweet> tweet, List<ReplyTweet> replyTweet) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
		this.tweet = tweet;
		this.replyTweet = replyTweet;
	}
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", email=" + email + ", password=" + password + ", contactNumber=" + contactNumber + ", tweet="
				+ tweet + ", replyTweet=" + replyTweet + "]";
	}
	private String id;
	public User(String id, String firstName, String lastName, String gender, String email, String password,
			String contactNumber, List<Tweet> tweet, List<ReplyTweet> replyTweet) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
		this.tweet = tweet;
		this.replyTweet = replyTweet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String password;
	private String contactNumber;
	private List<Tweet> tweet;
	private List<ReplyTweet> replyTweet;
	
	
	public List<Tweet> getTweet() {
		return tweet;
	}
	public void setTweet(List<Tweet> tweet) {
		this.tweet = tweet;
	}
	public List<ReplyTweet> getReplyTweet() {
		return replyTweet;
	}
	public void setReplyTweet(List<ReplyTweet> replyTweet) {
		this.replyTweet = replyTweet;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
}
