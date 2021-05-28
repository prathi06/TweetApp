import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TweetService } from 'src/app/Services/tweet.service';
import { UserServiceService } from 'src/app/Services/user-service.service';
import { ResetPasswordComponent } from '../reset-password/reset-password.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loginUser:any;
  constructor(
    private userService:UserServiceService,
    private modalService: NgbModal,
    private tweetService:TweetService,
    private route: ActivatedRoute,
    private activeModal: NgbActiveModal,
    private router: Router
  ) { 
    this.loginUser=this.userService.currentUser;
  }

  ngOnInit(): void {
  }

  open() {
    const modalRef = this.modalService.open(ResetPasswordComponent);
  }
  PostTweet(content)
  {
    this.modalService.open(content, { centered: true });
  }
  AddTweetToUser(content)
  {
      //alert(content);
      this.tweetService.postTweetToDB(this.loginUser.id,content).subscribe(
        data =>{
          console.log(data);
        }
      );
      //this.activeModal.close('Close click')
      //this.modal.dismiss('Cross click')
      //this.activeModal.close('Close click');
      this.router.navigate(['/home']);
  }
}
