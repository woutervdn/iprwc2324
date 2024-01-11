import { Injectable } from '@angular/core';
import {Product} from "../models/product";
import {ProductService} from "./product.service";
import {HttpClient} from "@angular/common/http";
import {Cart} from "../models/cart";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private apiUrl = 'http://localhost:8080/api/cart/';
  private userId: number = 1;

  constructor(private productService: ProductService, private http: HttpClient) { }

  getCart() {
    return this.http.get<Cart>(this.apiUrl + this.userId);
  }

  addToCart(cart: Cart) {
    return this.http.put<Cart>(this.apiUrl + this.userId, cart);
  }
}
