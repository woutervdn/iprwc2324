import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Product} from "../models/product";
import {Order} from "../models/order";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) { }

  index() {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);

    return this.http.get<Order[]>(this.apiUrl + 'order', {
      headers: header
    });
  }

  create(order: any) {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);

    return this.http.post(this.apiUrl + 'order', order, {
      headers: header
    });
  }

  delete(id: number) {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);

    return this.http.delete(this.apiUrl + 'order/' + id, {
      headers: header
    });
  }

}
