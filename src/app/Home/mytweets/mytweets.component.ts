import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TweetService } from 'src/app/Services/tweet.service';
import { UserServiceService } from 'src/app/Services/user-service.service';
import { ViewRepliesComponent } from 'src/app/Users/view-replies/view-replies.component';

@Component({
  selector: 'app-mytweets',
  templateUrl: './mytweets.component.html',
  styleUrls: ['./mytweets.component.css']
})
export class MytweetsComponent implements OnInit {

  myTweets:any;
  loginUser:any;
  myReplies:any;
  constructor(
    private tweetService:TweetService,
    private modalService: NgbModal,
    private userService:UserServiceService
  ) {
       this.loginUser=this.userService.currentUser;
       this.myTweets=this.tweetService.getOnlyMyTweets(this.loginUser.id).subscribe(data =>{
         this.myTweets=data;
       });
       console.log(this.myTweets);
    }

  ngOnInit(): void {
  }

  loadReplies(obj:any)
  {
      this.myReplies=null;
      this.tweetService.loadRepliesById(this.loginUser.id,obj.id).subscribe(data =>{
        console.log(data);
        this.myReplies=data[0]||{};
        const modalRef = this.modalService.open(ViewRepliesComponent,{ size: 'lg',scrollable: true });
      modalRef.componentInstance.replies = this.myReplies;
      modalRef.componentInstance.sourceTweet = obj;
      modalRef.componentInstance.isReply = false;
      });
      
  }
}
