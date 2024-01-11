import {Component, OnInit} from '@angular/core';
import {Cart} from "../../models/cart";
import {CartService} from "../cart.service";
import {Product} from "../../models/product";

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
    this.cartService.getCart().subscribe((response: Cart) => {
      this.cart = response;
    })
  }

  updateCart(product: Product) {
    this.cart.items.push({
      "id": 0,
      "amount": 1,
      "product": product
    })
    this.cartService.addToCart(this.cart);
  }

  ngOnInit(): void {
    this.fetchCart();
  }

}
