import {Component, Input} from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../product.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {

  @Input() product: Product = {category: "", description: "", id: 0, image: "", price: 0, title: ""};

  @Input() route: String = '';

  constructor(public productService: ProductService) {
  }

}
