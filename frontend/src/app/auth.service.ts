import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../models/user";
import {Product} from "../models/product";
import {LoginResponse} from "../models/login_response";
import {Subject} from "rxjs";
import {Router} from "@angular/router";
import {SnackbarService} from "./snackbar.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/';

  private authenticated = true;

  private admin = true;

  constructor(
    private http: HttpClient,
    private router: Router,
    private snackBarService: SnackbarService
  ) {}

  login(user: User, redirect?: string) {
    console.log('red', redirect);
    return this.http.post<any>(this.apiUrl + 'user/login', user).subscribe(res => {
      if (res.success) {
        localStorage.setItem('token', res.token);
        this.authenticated = true;
        this.admin = res.admin;
        this.router.navigate([redirect ?? '/']);
        this.snackBarService.openSnackBar('Logged in successfully');
      } else {
        this.snackBarService.openSnackBar('Incorrect Login');
        this.router.navigate(['/login']);
      }
    });
  }

  logout() {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);
    return this.http.get(this.apiUrl + 'user/logout', {
      headers: header
    }).subscribe({
      next: () => {
        localStorage.setItem('token', '');
        this.authenticated = false;
        this.admin = false;
        this.snackBarService.openSnackBar('Logged out');
      }
    })
  }

  register(user: User) {
    return this.http.post(this.apiUrl + 'user', user).subscribe({
      next: data => {
        this.snackBarService.openSnackBar('Registered Succesfully, please login.');
      },
      error: err => {
        console.log(err);
        this.snackBarService.openSnackBar('Could not register, please try again: ');
      }
    });
  }

  isAuthenticated() {
    return this.authenticated;
  }

  isAdmin(): boolean {
    return this.admin;
  }

}
