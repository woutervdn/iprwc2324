import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {Product} from "../models/product";
import {LoginResponse} from "../models/login_response";
import {Subject} from "rxjs";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/';

  private authenticated = false;

  private admin = false;

  constructor(private http: HttpClient, private router: Router) { }

  login(user: User) {
    return this.http.post<any>(this.apiUrl + 'user/login', user).subscribe(res => {
      if (res.success) {
        localStorage.setItem('token', res.token);
        this.authenticated = true;
        this.router.navigate(['/']);
      } else {
        this.router.navigate(['/login']);
      }
    });
  }

  logout() {
    localStorage.setItem('token', '');
    this.authenticated = false;
    this.admin = false;
  }

  register(user: User) {
    return this.http.post(this.apiUrl + 'user', user);
  }

  isAuthenticated() {
    return this.authenticated;
  }

  isAdmin(): boolean {
    return this.admin;
  }

}
