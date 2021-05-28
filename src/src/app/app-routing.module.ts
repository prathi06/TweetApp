import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MytweetsComponent } from './Home/mytweets/mytweets.component';
import { WelcomeComponentComponent } from './Home/welcome-component/welcome-component.component';
import { LoginComponent } from './Users/login/login.component';
import { SignUpComponent } from './Users/sign-up/sign-up.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: SignUpComponent },
  { path: 'home', component: WelcomeComponentComponent },
  { path: 'mytweet', component: MytweetsComponent },
  { path: '**', component: LoginComponent },
  { path: '', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
