import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class TweetService {

  allTweets:any=[];
  myTweets:any;
  baseUrl:String="http://localhost:8222/api/v1.0/tweets";
  constructor(
    private http: HttpClient
  ) {
   
   }

  getAllTweets()
  {

    return this.http.get<any>(this.baseUrl+'/all');
  }
  getOnlyMyTweets(userId:any)
  {
    return this.http.get<any>(this.baseUrl+'/users/'+userId);
  }

  postTweetToDB(id:any,content:String)
  {
       return this.http.post<any>(this.baseUrl+'/'+id+'/add',content);
  }
  loadRepliesById(userId:any,tweetId:any)
  {
    return this.http.get<any>(this.baseUrl+'/'+userId+'/reply/'+tweetId);
  }

  postRetweet(userId:any,postId:any,content:any)
  {
    return this.http.post<any>(this.baseUrl+'/'+userId+'/reply/'+postId,content);
  }
  likeTweet(userId:any,tweetId:any)
  {
    //http://localhost:8220/api/v1.0/tweets/608d8b8624014f23dc5d69c2/like/608d8b9c24014f23dc5d69c3
    return this.http.put<any>(this.baseUrl+'/'+userId+'/like/'+tweetId,{});
  }
}
