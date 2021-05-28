import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/Users';
import { UserServiceService } from 'src/app/Services/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  res:any;
  error:String="";
  
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private userService:UserServiceService
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });
  }

  get f() { return this.form.controls; }

  onSubmit()
  {
    //alert(this.f.username.value+""+ this.f.password.value);
   if(this.validateUserLogin())
   {
        this.res=this.userService.login({email:this.f.username.value,password:this.f.password.value}).subscribe(data =>{
          console.log(data);
          this.res=data;
          if(this.res!="Login Failed")
          {
                this.userService.currentUser=this.res;
                this.router.navigate(['/home']);
          }
          else
          {
             this.error="You are not registered to Tweet app click register to Register";
              //this.router.navigate(['/register']);
          }

        });
          
   }
  }

  validateUserLogin()
  {
    let status=true;
     const username=this.f.username.value;
     const password=this.f.password.value;
     if(username==""||password=="")
     {
       this.error="Please fill the details to login";
        status=false;
     }
     else if(!this.validateEmail(username))
     {
        this.error="please enter the email in valid formate";
        status=false;
     }
     return status;
  }
  validateEmail(email)
  {
    const re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/;
    return re. test(email. toLowerCase());
  }

}
