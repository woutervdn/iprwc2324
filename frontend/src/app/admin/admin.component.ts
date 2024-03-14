import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ProductService} from "../product.service";
import {Product} from "../../models/product";
import {Order} from "../../models/order";
import {OrderService} from "../order.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {User} from "../../models/user";
import {UserService} from "../user.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit, OnChanges {

  public products: Product[] = [];
  public orders: Order[] = [];
  public users: any[] = [];

  id: number = 0;
  title: string = '';
  description: string = '';
  price: number = 0;
  category: string = '';
  image: string = '';

  crudMode: string = 'create'

  constructor(
    private productService: ProductService,
    private orderService: OrderService,
    private userService: UserService,
    public snackBar: MatSnackBar
  ) {}
  ngOnChanges(): void {
    this.fetchProducts();
    this.fetchOrders();
    this.fetchUsers();
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, '', {
      verticalPosition: "top",
      duration: 5000,
    });
  }

  ngOnInit() {
    this.fetchProducts();
    this.fetchOrders();
    this.fetchUsers();
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

  fetchUsers() {
    this.userService.getAll().subscribe({
      next: users => {
        this.users = users;
      }
    })
  }

  addProduct(): void {
    let prodObj = {
      "title": this.title,
      "description": this.description,
      "image": "test.png",
      "price": this.price,
      "category": this.category
    }

    this.productService.create(prodObj).subscribe({
      next: data => this.fetchProducts(),
      error: err => {
        if (err.status === 401) {
          this.openSnackBar('Unauthorized');
        } else {
          this.openSnackBar(err);
        }
      }
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

  resetForm() {
    this.id = 0;
    this.title = '';
    this.image = '';
    this.description = '';
    this.price = 0;
    this.category = '';

    this.crudMode = 'create';
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

    this.productService.update(prodObj).subscribe(() => {
      this.fetchProducts();
    });

    this.resetForm();
  }

  removeProduct(id: number): void {
    this.productService.delete(id).subscribe(() => {
      this.fetchProducts();
    });
  }

}
