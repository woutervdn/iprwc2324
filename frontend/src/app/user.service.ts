import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/';
  constructor(private http: HttpClient) { }

  getAll() {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);

    return this.http.get<Object[]>(this.apiUrl + 'user', {
      headers: header
    });
  }

}
