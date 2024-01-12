import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../product.service";
import {CartService} from "../cart.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {

  @Input() product: Product = {category: "", description: "", id: 0, image: "", price: 0, title: ""};

  @Input() route: String = '';

  @Output('deleteProduct') deleteProduct: EventEmitter<number> = new EventEmitter<number>();

  @Output('productID') productID: EventEmitter<number> = new EventEmitter<number>();

  constructor(public productService: ProductService, private cartService: CartService) {
    console.log(this.product)
  }

  delete() {
    this.deleteProduct.emit(this.product.id);
  }

  addToCart() {
    console.log(this.product.id);
    this.cartService.addToCart(this.product)
  }

}
