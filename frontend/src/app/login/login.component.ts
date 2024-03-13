import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Router} from "@angular/router";
import {SnackbarService} from "../snackbar.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: any = null;
  password: any = null;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
  ) {}

  login() {
    this.authService.login({ username: this.username, password: this.password}, this.route.snapshot.queryParams['redirect']);
  }

  register() {
    this.authService.register({ username: this.username, password: this.password})
  }

}
