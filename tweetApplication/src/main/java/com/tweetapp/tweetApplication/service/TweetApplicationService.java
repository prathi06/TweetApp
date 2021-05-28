package com.tweetapp.tweetApplication.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.tweetapp.tweetApplication.model.ReplyTweet;
import com.tweetapp.tweetApplication.model.Tweet;
import com.tweetapp.tweetApplication.model.User;
import com.tweetapp.tweetApplication.repo.TweetApplicationRepositoryImpl;

@Service
public class TweetApplicationService {

	@Autowired
	TweetApplicationRepositoryImpl tweetrepo;

	private static final Logger logger = LoggerFactory.getLogger(TweetApplicationService.class);


	public List<User> getAllUserTweets() {
		return tweetrepo.getAllUserTweets("firstName", "lastName", "tweet");

	}

	public List<User> getAllUsers() {
		return tweetrepo.getAllUsers("firstName", "lastName", "gender", "email");

	}

	public User getUserTweets(String userId) {
		return tweetrepo.getUserTweets(userId, "firstName", "lastName", "tweet");

	}

	public void addPost(String userId, String tweetMessage) {
		tweetrepo.addPost(userId, tweetMessage);
	}

	public void updatePost(String userId, String tweetId, Tweet tweet) {
		tweetrepo.updatePost(userId, tweetId, tweet);
	}

	public void deletePost(String userId, String tweetId) {
		tweetrepo.deletePost(userId, tweetId);
	}

	public void addReplyPost(String userId, String tweetId, ReplyTweet replyTweet) {
		tweetrepo.addReplyPost(userId, tweetId, replyTweet);
	}

	public List<User> getReplyPost(String userId, String tweetId) {
		return tweetrepo.getReplyPost(userId, tweetId);
	}

	public String register(User user) {
		return tweetrepo.register(user);
	}

	public User login(String email, String password) {
		return tweetrepo.login(email, password);
	}

	public String resetPassword(String userId, String password) {
		return tweetrepo.resetPassword(userId, password);
	}

	public void updateCount(String UserId, String tweetId) {
		tweetrepo.updateCount(UserId, tweetId);
	}

	public List<User> searchUser(String userName) {
		return tweetrepo.searchUser(userName, "firstName", "lastName");
	}

}
