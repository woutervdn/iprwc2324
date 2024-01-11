import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ProductService} from "../product.service";
import {Product} from "../../models/product";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit, OnChanges {

  constructor(private productService: ProductService) {
  }
  ngOnChanges(): void {
    this.fetchProducts();
  }

  ngOnInit() {
    this.fetchProducts();
  }

  public fetchProducts() {
    this.productService.index().subscribe((data) => {
      this.products = data;
      console.log("data", this.products);
    });
  }

  public products: Product[] = [];

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
    this.fetchProducts();
    console.log(prodObj);
  }

  removeProduct(id: number): void {
    this.productService.delete(id);
    this.products = this.products.filter((product: Product) => product.id !== id);
  }

}
