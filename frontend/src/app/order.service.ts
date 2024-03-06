import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../models/product";
import {Order} from "../models/order";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<Order[]>(this.apiUrl + 'order');
  }

}
