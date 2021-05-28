import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { UserServiceService } from 'src/app/Services/user-service.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  loginUser:any;
  constructor(
    public activeModal: NgbActiveModal,
    private userService:UserServiceService
  ) { 
    this.loginUser=this.userService.currentUser;
  }


  ngOnInit(): void {
  }
  savePassword(newPassword:any)
  {
     console.log(newPassword);
     console.log(this.loginUser.email);
     this.userService.resetPassword(this.loginUser.id,newPassword).subscribe(data =>{
       console.log(data);
     });
     this.activeModal.close('Close click');
  }

}
