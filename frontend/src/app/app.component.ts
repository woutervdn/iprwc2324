import {Component, OnInit} from '@angular/core';
import { Product } from '../models/product';
import {ProductService} from "./product.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'IPRWC';

  public products: Product[] = [];

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productService.productIndex().subscribe((data) => {
      this.products = data;
      console.log("data", this.products);
    });
  }


}
