import { Component } from '@angular/core';
import {ProductService} from "../product.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  constructor(private productService: ProductService) {
  }


  title: string = '';
  description: string = '';
  price: number = 0;
  category: string = '';

  addProduct(): void {
    let prodObj = {
      "title": this.title,
      "description": this.description,
      "image": "test.png",
      "price": this.price,
      "category": this.category
    }

    this.productService.create(prodObj);
    console.log(prodObj);
  }

}
