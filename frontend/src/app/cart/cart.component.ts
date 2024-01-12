import {Component, OnInit} from '@angular/core';
import {Cart} from "../../models/cart";
import {CartService} from "../cart.service";
import {Product} from "../../models/product";
import {formatCurrency, formatNumber} from "@angular/common";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public cart: Cart = {
    id: 0,
    user_id: 0,
    items: [],
    total: 0
  };

  constructor(private cartService: CartService) {
  }

  fetchCart() {
    this.cart = this.cartService.getCart();
  }

  clearCart() {
    this.cartService.emptyCart();
  }

  ngOnInit(): void {
    this.fetchCart();
  }

  protected readonly formatCurrency = formatCurrency;
  protected readonly formatNumber = formatNumber;
}
