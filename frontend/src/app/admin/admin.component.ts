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

  public products: Product[] = [];
  public orders: Order[] = [];

  id: number = 0;
  title: string = '';
  description: string = '';
  price: number = 0;
  category: string = '';
  image: string = '';

  crudMode: string = 'create'

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

  fetchProducts() {
    this.productService.index().subscribe(products => {
      this.products = products;
      console.log("products", this.products);
    });
  }

  fetchOrders() {
    this.orderService.index().subscribe((data) => {
      this.orders = data;
      console.log("orders", this.orders);
    });
  }

  addProduct(): void {
    let prodObj = {
      "title": this.title,
      "description": this.description,
      "image": "test.png",
      "price": this.price,
      "category": this.category
    }

    this.productService.create(prodObj).subscribe(data => {
      this.fetchProducts();
    });
  }

  setProduct(id: number) {
    let prod: any = this.products.find((product) => {
      return product.id === id;
    })

    this.id = id;
    this.title = prod.title;
    this.image = prod.image;
    this.description = prod.description;
    this.price = prod.price;
    this.category = prod.category.toLowerCase();

    this.crudMode = 'update';
  }

  editProduct(id: number): void {
    let prodObj = {
      "id": id,
      "title": this.title,
      "description": this.description,
      "image": this.image,
      "price": this.price,
      "category": this.category
    }

    this.productService.update(prodObj);
    this.fetchProducts();
  }

  removeProduct(id: number): void {
    this.productService.delete(id);
    this.products = this.products.filter((product: Product) => product.id !== id);
  }

}
