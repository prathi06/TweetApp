package com.tweetapp.tweetApplication.model;

public class ReplyTweet {
  
	
	private String id;
	private String repliedBy;
	private String replyMessage;
	private String replyDate;
	public String getId() {
		return id;
	}
	public ReplyTweet() {}
	public ReplyTweet(String id, String repliedBy, String replyMessage, String replyDate) {
		super();
		this.id = id;
		this.repliedBy = repliedBy;
		this.replyMessage = replyMessage;
		this.replyDate = replyDate;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ReplyTweet [id=" + id + ", repliedBy=" + repliedBy + ", replyMessage=" + replyMessage + ", replyDate="
				+ replyDate + "]";
	}
	public String getRepliedBy() {
		return repliedBy;
	}
	public void setRepliedBy(String repliedBy) {
		this.repliedBy = repliedBy;
	}
	public String getReplyMessage() {
		return replyMessage;
	}
	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
}
