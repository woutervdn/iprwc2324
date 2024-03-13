import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Product} from "../models/product";
import {data} from "autoprefixer";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient, private loginService: AuthService) { }

  index() {
    return this.http.get<Product[]>(this.apiUrl + 'product');
  }

  create(product: any) {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);

    return this.http.post<Product>(this.apiUrl + 'product', product, {
      headers: header
    });
  }

  update(product: any) {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);

    return this.http.put<Product>(this.apiUrl + 'product', product, {
      headers: header
    });
  }

  delete(productId: number) {
    let header = new HttpHeaders();
    header = header.set('Authorization', `${localStorage.getItem('token')}`);

    return this.http.post<Product>(this.apiUrl + 'product/' + productId, {
      headers: header
    });
  }


}
