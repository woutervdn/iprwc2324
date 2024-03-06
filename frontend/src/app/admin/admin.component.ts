import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ProductService} from "../product.service";
import {Product} from "../../models/product";
import {Order} from "../../models/order";
import {OrderService} from "../order.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit, OnChanges {

  constructor(
    private productService: ProductService,
    private orderService: OrderService
  ) {}
  ngOnChanges(): void {
    this.fetchProducts();
    this.fetchOrders();
  }

  ngOnInit() {
    this.fetchProducts();
    this.fetchOrders();
  }

  public fetchProducts() {
    this.productService.index().subscribe((data) => {
      this.products = data;
    });
  }

  public fetchOrders() {
    this.orderService.index().subscribe((data) => {
      this.orders = data;
      console.log("orders", this.orders);
    });
  }

  public products: Product[] = [];
  public orders: Order[] = [];

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
