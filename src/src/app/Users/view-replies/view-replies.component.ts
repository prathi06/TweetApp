import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TweetService } from 'src/app/Services/tweet.service';
import { UserServiceService } from 'src/app/Services/user-service.service';
import { DatePipe } from '@angular/common'
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-view-replies',
  templateUrl: './view-replies.component.html',
  styleUrls: ['./view-replies.component.css']
})
export class ViewRepliesComponent implements OnInit {
  @Input() replies:any=[];
  @Input() isReply:boolean=true;
  @Input() sourceTweet:any="";
  loginUser:any;
  payload:any;
  constructor(public activeModal: NgbActiveModal,
    private tweetService:TweetService,
    public datepipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router,
    private userService:UserServiceService)
   {
     this.loginUser=this.userService.currentUser;
    }

  ngOnInit(): void {
  }

  addRetwwt(reTweetMsg:any,sourcetweet:any)
  {
      //alert(reTweetMsg);
      this.payload={
        "id": sourcetweet,
         "repliedBy": this.loginUser.firstName,
        "replyDate": this. datepipe. transform(new Date(), 'yyyy-MM-dd'),
         "replyMessage":reTweetMsg
      };
   
       this.tweetService.postRetweet(this.loginUser.id,sourcetweet,this.payload).subscribe(data =>{
        console.log(data);
        this.activeModal.close('Close click');
        this.router.navigate(['/home']);
      });
     
  }
}
