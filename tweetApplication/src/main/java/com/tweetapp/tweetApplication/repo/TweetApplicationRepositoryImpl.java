package com.tweetapp.tweetApplication.repo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.tweetapp.tweetApplication.model.ReplyTweet;
import com.tweetapp.tweetApplication.model.Tweet;
import com.tweetapp.tweetApplication.model.User;

public class TweetApplicationRepositoryImpl {

	@Autowired
	TweetApplicationRepository tweetAppRepo;
	@Autowired
	MongoTemplate mongotemplate;
	@Autowired
	MongoOperations mongoOperations;

	public List<User> getAllUserTweets(String... fields) {
		System.out.println(">>>>>inside repo layer");
		Query query = new Query();
		if (fields != null)
			for (String field : fields)
				query.fields().include(field);
		return mongotemplate.find(query, User.class);

	}

	public List<User> getAllUsers(String... fields) {
		Query query = new Query();
		if (fields != null)
			for (String field : fields)
				query.fields().include(field);
		return mongotemplate.find(query, User.class);

	}

	public User getUserTweets(String userId, String... fields) {
		Query query = new Query();

		query.addCriteria(new Criteria("_id").is(userId));
		if (fields != null)
			for (String field : fields)
				query.fields().include(field);
		return mongotemplate.findOne(query, User.class);
	}

	public void addPost(String userId, String tweetMessage) {
		Tweet tweet = new Tweet(tweetMessage);
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(userId));
		Update update = new Update().addToSet("tweet", tweet);
		mongotemplate.updateMulti(query, update, User.class);
	}

	public void updatePost(String userId, String tweetId, Tweet tweet) {

		Query query4 = new Query().addCriteria(Criteria.where("tweet").elemMatch(Criteria.where("_id").is(tweetId)));
		Update update = new Update().set("tweet.$.postTweet", tweet.getPostTweet());
		mongotemplate.updateFirst(query4, update, User.class);

	}

	public void deletePost(String userId, String tweetId) {
		Query query = new Query(Criteria.where("tweet").elemMatch(Criteria.where("_id").is(tweetId)));
		Update update = new Update().pull("tweet", new Query(Criteria.where("_id").is(tweetId)));
		mongotemplate.updateMulti(query, update, User.class);

	}

	public void addReplyPost(String userId, String tweetId, ReplyTweet replyTweet) {
		Query query = new Query(Criteria.where("tweet").elemMatch(Criteria.where("_id").is(tweetId)));
		Update update = new Update().addToSet("replyTweet", replyTweet);
		mongotemplate.updateMulti(query, update, User.class);

	}

	public List<User> getReplyPost(String userId, String tweetId) {

		List<AggregationOperation> list = new ArrayList<AggregationOperation>();
		list.add(Aggregation.unwind("replyTweet"));
		list.add(Aggregation.match(Criteria.where("replyTweet._id").is(new ObjectId(tweetId))));
		list.add(Aggregation.group("firstName", "lastName").push("replyTweet").as("replyTweet"));
		list.add(Aggregation.project("firstName", "lastName", "replyTweet"));
		TypedAggregation<User> agg = Aggregation.newAggregation(User.class, list);
		return mongoOperations.aggregate(agg, User.class, User.class).getMappedResults();

	}

	public String register(User user) {

		User returnedUser = tweetAppRepo.findByEmail(user.getEmail());
		if (returnedUser == null) {
			tweetAppRepo.save(user);
			return "User Saved Successfully";
		} else {
			return "User email already exists";
		}

	}

	public User login(String email,String password) {
		
		User sendUser=null;
		User returnedUser = tweetAppRepo.findByEmail(email);
			if(returnedUser!=null)
			{
				if(password.equals(returnedUser.getPassword()))
					return new User(returnedUser.getId(),returnedUser.getFirstName(),returnedUser.getLastName(),returnedUser.getGender(),returnedUser.getEmail(),null,null,null,null);
					
			}
		
			return sendUser;
	}

	public String resetPassword(String userId,String password) {
		
			Query query4 = new Query().addCriteria(Criteria.where("_id").is(userId));
			Update update = new Update().set("password", password);
			mongotemplate.updateFirst(query4, update, User.class);
			return "Password Changed Successfully";
		
	}

	public void updateCount(String userId, String tweetId) {

		Query query = new Query(
				Criteria.where("tweet").elemMatch(Criteria.where("_id").is(tweetId)));
		Update update = new Update().inc("tweet.$.count", 1);
		mongotemplate.updateMulti(query, update, User.class);
	}

	public List<User> searchUser(String name, String... fields) {
		Query query = new Query();
		query.limit(10);
		if (fields != null)
			for (String field : fields)
				query.fields().include(field);
		query.addCriteria(Criteria.where("firstName").regex(name));
		return mongoOperations.find(query, User.class);

	}
}
