import { Injectable } from '@angular/core';
import { User } from '../models/Users';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  allUsers:any=[];
  currentUser:any={email:"fackUser@gmamil.com",
id:"fackid"};
  baseUrl:String="http://localhost:8222/api/v1.0/tweets";
 
  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) { 
        if(this.currentUser.id=="fackid")
        {
              
          this.router.navigate(['/login']);
        }
  }

  getCurrentUserFromSession()
  {
     this.currentUser = JSON.parse(localStorage.getItem("userData"));
  }

  login(loginUser:any):Observable<any>
  {
      console.log(loginUser.email);
      console.log(loginUser.password);
      return this.http.get<any>(this.baseUrl+'/login?email='+loginUser.email+'&password='+loginUser.password);
  }

  registerUser(newUser:any):Observable<any>
  {
     console.log(this.http.post<String>(this.baseUrl+'/register', newUser));
    return this.http.post<any>(this.baseUrl+'/register', newUser);
  }

  resetPassword(id:any,newPassword:any)
  {
    return this.http.get<any>(this.baseUrl+'/'+id+'/forgot?password='+newPassword);

  }

}
