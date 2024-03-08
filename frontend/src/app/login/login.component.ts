import { Component } from '@angular/core';
import {LoginService} from "../login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string = 'admin';
  password: string = 'test1234';

  constructor(
    private loginService: LoginService
  ) {}

  login() {
    this.loginService.login({ username: this.username, password: this.password}).subscribe(res => {
      console.log("response", res);
      if (res.token){
        localStorage.setItem('token', res.token);
      }
    }, error => {
      console.log("error", error)
    })
  }

}
