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
    return this.http.post(this.apiUrl + 'product', product).subscribe(data => {
      console.log(data);
    })
  }

  delete(productId: number) {
    return this.http.delete(this.apiUrl + 'product/' + productId).subscribe(data => {
      console.log(data);
    })
  }


}
