import { Component } from '@angular/core';
import {AuthService} from "../auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string = '';
  password: string = '';

  constructor(
    private authService: AuthService,
    public snackbar: MatSnackBar
  ) {}

  login() {
    this.authService.login({ username: this.username, password: this.password});
  }

  register() {
    this.authService.register({ username: this.username, password: this.password})
  }

}
