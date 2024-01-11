import { Injectable } from '@angular/core';
import {Product} from "../models/product";
import {ProductService} from "./product.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cart: Product[] = []
  constructor(private productService: ProductService, private http: HttpClient) { }

  // addToCart(id) {
  //
  // }
}
