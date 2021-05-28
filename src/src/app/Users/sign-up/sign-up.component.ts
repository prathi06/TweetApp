import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/Users';
import { UserServiceService } from 'src/app/Services/user-service.service';
@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  form: FormGroup;
  result=false;
  addUser:any;
  newData:any;
  errorMsg="";
  enableButton:boolean=true;
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private userService:UserServiceService
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: [''],
      password: [''],
      email:[''],
      firstname:[''],
      lastname:[''],
      contact:[''],
      gender:[''],
      conformpassword:[''],
  });
  }

  get f() { return this.form.controls; }

  onSubmit()
  {

   
      this.newData={
      "contactNumber": this.f.contact.value,
      "email": this.f.email.value,
      "firstName": this.f.firstname.value,
      "gender": this.f.gender.value,
      "lastName": this.f.lastname.value,
      "password": this.f.password.value,
      "replyTweet": null,
      "tweet": null
    };
    this.validateForm(this.newData);
    console.log(  this.validateForm(this.newData));
    if( this.validateForm(this.newData))
    {
                        this.result=null;
                        this.userService.registerUser(this.newData).subscribe(data => {
                          this.result=data;
                          if(this.result)
                          {
                
                                this.router.navigate(['/login']);
                          }
                          else
                          {
                            this.router.navigate(['/register']);
                          }
                        });;
                      
     }
  }


  validateForm(data:any)
  {
    this.errorMsg="";
    this.enableButton=true;
      if(data.firstName==""||data.lastName==""||data.email==""||data.password==""||data.contactNumber==""||data.gender=="")
      {
        this.errorMsg="please fill all details";
        this.enableButton=false;
      }
     else if(this.f.conformpassword.value!=this.f.password.value)
      {
        console.log(this.f.conformpassword.value+"-----"+this.f.password.value);
        this.errorMsg="password and conform password must be same";
        this.enableButton=false;
      }
     else if(!this.validateEmail(data.email))
      {
        this.errorMsg="Please enter the email address in valid formate";
        this.enableButton=false;
      }
     else if(data.contactNumber.toString().length!=10)
      {
        this.errorMsg="Contact Number must contain exactly 10 digit";
        this.enableButton=false;
      }
    //  alert(this.enableButton);
      if(this.enableButton==false)
      {
            return false;
      }
      return true;

  }


  validateEmail(email)
  {
    const re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/;
    return re.test(email.toLowerCase());
  }
}
