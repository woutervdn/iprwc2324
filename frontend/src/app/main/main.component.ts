import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../product.service";
import {CartService} from "../cart.service";
import {Cart} from "../../models/cart";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  public products: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.productService.index().subscribe((data) => {
      this.products = data;
      console.log("data", this.products);
    });
  }

}
