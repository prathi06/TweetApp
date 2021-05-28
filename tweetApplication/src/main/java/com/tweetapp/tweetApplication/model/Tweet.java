package com.tweetapp.tweetApplication.model;

import org.bson.types.ObjectId;

public class Tweet {
	
	public Tweet() {}
	public Tweet(String postTweet) {
		super();
		this.id = new ObjectId().toString();
		this.postTweet = postTweet;
		
	}
	private String id;
	private String postTweet;
	private int count;

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPostTweet() {
		return postTweet;
	}
	public void setPostTweet(String postTweet) {
		this.postTweet = postTweet;
	}
	
	@Override
	public String toString() {
		return "Tweet [id=" + id + ", postTweet=" + postTweet + ", count=" + count + "]";
	}
	
	
}
