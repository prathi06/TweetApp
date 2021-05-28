package com.tweetapp.tweetApplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.tweetApplication.model.ReplyTweet;
import com.tweetapp.tweetApplication.model.Tweet;
import com.tweetapp.tweetApplication.model.User;
import com.tweetapp.tweetApplication.service.TweetApplicationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1.0")
public class TweetApplicationController {

	@Autowired
	TweetApplicationService tweetAppService;
	

	protected ObjectMapper mapper = new ObjectMapper();

	@GetMapping("/tweets/all")
	public String getAllUserTweets() throws JsonProcessingException {
		List<User> userTweets = tweetAppService.getAllUserTweets();
		System.out.println("??????????"+userTweets);
		return mapper.writeValueAsString(userTweets);
	}

	@GetMapping("/tweets/users/all")
	public String getAllUsers() throws JsonProcessingException {
		List<User> users = tweetAppService.getAllUsers();

		return mapper.writeValueAsString(users);
	}

	@GetMapping("/tweets/users/{userId}")
	public String getUserTweets(@PathVariable String userId) throws JsonProcessingException {
		User users = tweetAppService.getUserTweets(userId);
		return mapper.writeValueAsString(users);
	}

	@PostMapping("/tweets/{userId}/add")
	public void addPost(@PathVariable String userId, @Valid @RequestBody String userDetails) {
		tweetAppService.addPost(userId, userDetails);
	}


	@DeleteMapping("/tweets/{userId}/delete/{tweetId}")
	public void deletePost(@PathVariable("userId") String userId, @PathVariable("tweetId") String tweetId) {
		tweetAppService.deletePost(userId, tweetId);
	}

	@PostMapping("/tweets/{userId}/reply/{tweetId}")
	public void addReplyPost(@PathVariable("userId") String userId, @PathVariable("tweetId") String tweetId,
			@Valid @RequestBody ReplyTweet replyTweet) {
		tweetAppService.addReplyPost(userId, tweetId, replyTweet);
	}

	@GetMapping("/tweets/{userId}/reply/{tweetId}")
	public String getReplyTweet(@PathVariable("userId") String userId, @PathVariable("tweetId") String tweetId)
			throws JsonProcessingException {
		List<User> replyTweet = tweetAppService.getReplyPost(userId, tweetId);
		return mapper.writeValueAsString(replyTweet);
	}

	
	@PostMapping("tweets/register")
	public String addUser(@Valid @RequestBody User user) throws JsonProcessingException {
		return mapper.writeValueAsString(tweetAppService.register(user));
	}

	@GetMapping("tweets/login")
	public String login(@RequestParam String email, @RequestParam String password) throws JsonProcessingException {
		User userDetails = tweetAppService.login(email, password);
		if (userDetails != null)
			return mapper.writeValueAsString(userDetails);
		else
			return mapper.writeValueAsString("Login Failed");
	}

	@GetMapping("tweets/{userId}/forgot")
	public String resetPassword(@PathVariable String userId, @RequestParam String password) throws JsonProcessingException {
		return mapper.writeValueAsString(tweetAppService.resetPassword(userId, password));
	}

	@PutMapping("tweets/{userId}/like/{tweetId}")
	public void updateCount(@PathVariable("userId") String userId, @PathVariable("tweetId") String tweetId) {
		tweetAppService.updateCount(userId, tweetId);
	}

	@GetMapping("tweets/user/search/{username}")
	public String searchUser(@PathVariable("username") String userName) throws JsonProcessingException {
		List<User> user = tweetAppService.searchUser(userName);
		return mapper.writeValueAsString(user);
	}

}
