import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Users/login/login.component';
import { SignUpComponent } from './Users/sign-up/sign-up.component';
import { WelcomeComponentComponent } from './Home/welcome-component/welcome-component.component';
import { ResetPasswordComponent } from './Users/reset-password/reset-password.component';
import { MytweetsComponent } from './Home/mytweets/mytweets.component';
import { HeaderComponent } from './Users/header/header.component';
import { ViewRepliesComponent } from './Users/view-replies/view-replies.component';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignUpComponent,
    WelcomeComponentComponent,
    ResetPasswordComponent,
    MytweetsComponent,
    HeaderComponent,
    ViewRepliesComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [NgbActiveModal,DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
