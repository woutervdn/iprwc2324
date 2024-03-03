import { Injectable } from '@angular/core';
import {Product} from "../models/product";
import {ProductService} from "./product.service";
import {HttpClient} from "@angular/common/http";
import {Cart} from "../models/cart";
import {logMessages} from "@angular-devkit/build-angular/src/tools/esbuild/utils";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  public cart: Cart = {
    id: 0,
    user_id: 0,
    items: [],
    total: 0
  };

  addToCart(product: Product) {
    let item = this.cart.items.find(item => item.product.id === product.id);
    if (item !== undefined) {
      item.amount += 1;
    }
    else {
      this.cart.items.push({
        "id": 0,
        "amount": 1,
        "product": product
      });
    }
    this.cart.total += product.price;
  }

  emptyCart() {
    this.cart.items = [];
    this.cart.total = 0;
  }

  constructor(private productService: ProductService) { }

  getCart() {
    return this.cart
  }

}
