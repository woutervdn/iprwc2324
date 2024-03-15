import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<any[]>(this.apiUrl + 'category');
  }

  create(category: any) {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);

    return this.http.post(this.apiUrl + 'category', category, {
      headers: header
    });
  }

  update(category: any) {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);

    return this.http.put(this.apiUrl + 'category', category, {
      headers: header
    });
  }

  delete(categoryId: number) {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);

    return this.http.delete(this.apiUrl + 'category/' + categoryId, {
      headers: header
    });
  }
}
