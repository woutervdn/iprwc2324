import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {Product} from "../models/product";
import {LoginResponse} from "../models/login_response";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) { }

  login(user: User) {
    return this.http.post<any>(this.apiUrl + 'user/login', user);
  }

  register(user: User) {
    return this.http.post(this.apiUrl + 'user', user);
  }

  isAuthenticated() {
    let token = localStorage.getItem('token');
    this.http.post(this.apiUrl + 'user/checkalive', token).pipe();

  }

}
