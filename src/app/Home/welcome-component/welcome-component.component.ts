import { Component, Input, OnInit } from '@angular/core';
import { TweetService } from 'src/app/Services/tweet.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ResetPasswordComponent } from 'src/app/Users/reset-password/reset-password.component';
import { UserServiceService } from 'src/app/Services/user-service.service';

import { ViewRepliesComponent } from 'src/app/Users/view-replies/view-replies.component';
@Component({
  selector: 'app-welcome-component',
  templateUrl: './welcome-component.component.html',
  styleUrls: ['./welcome-component.component.css']
})
export class WelcomeComponentComponent implements OnInit {
allTweets:any=[];
myReplies:any=[];
jsonData:any=[];
loginUser:any;
  constructor(
    private tweetService:TweetService,
    private modalService: NgbModal,
    private userService:UserServiceService
  )
   {
     this.allTweets=null;
     this.tweetService.getAllTweets().subscribe(data =>{
       this.allTweets=data;
       console.log(this.allTweets);
       this.prepareJson();
     });

     this.loginUser=this.userService.currentUser;
    }

  ngOnInit(): void {
  }
  open() {
    const modalRef = this.modalService.open(ResetPasswordComponent);
  }
  loadReplies(obj:any)
  {
    this.myReplies=null;
 
    this.tweetService.loadRepliesById(this.loginUser.id,obj.id).subscribe(data =>{
      this.myReplies=data[0]||{};
      const modalRef = this.modalService.open(ViewRepliesComponent,{ size: 'lg',scrollable: true });
      modalRef.componentInstance.replies = this.myReplies;
    //  alert(obj.id);
    console.log(obj);
      modalRef.componentInstance.sourceTweet = obj;
      modalRef.componentInstance.isReply = true;
    });

  }
  addLike(id:any)
  {
     // alert("added like for "+id);

      this.tweetService.likeTweet(this.loginUser.id,id).subscribe(data => {
        this.tweetService.getAllTweets().subscribe(data =>{
          this.allTweets=data;
          console.log(this.allTweets);
          this.prepareJson();
        });
      });
  }


  prepareJson()
  {
    this.jsonData=[];
     this.allTweets.forEach(element => {
       if(element.tweet!=undefined&&element.tweet!={}){
                        element.tweet.forEach(element1 => {
                          let sample={
                            firstName:element.firstName,
                            lastName:element.lastName,
                            id:element1.id,
                            postTweet:element1.postTweet,
                            count:element1.count

                           };
                       this.jsonData.push(sample);
                           
       });

                        }
     
     });

     this.allTweets=this.jsonData;
     console.log("----------"+this.allTweets);
  }
}
