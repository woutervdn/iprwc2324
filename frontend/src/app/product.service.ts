import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../models/product";
import {data} from "autoprefixer";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<Product[]>(this.apiUrl + 'product');
  }

  create(product: any) {
    return this.http.post<Product>(this.apiUrl + 'product', product);
  }

  update(product: any) {
    return this.http.put<Product>(this.apiUrl + 'product', product);
  }

  delete(productId: number) {
    return this.http.delete<Product>(this.apiUrl + 'product/' + productId);
  }


}
