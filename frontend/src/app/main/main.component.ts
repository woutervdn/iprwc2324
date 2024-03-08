import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../product.service";
import {CartService} from "../cart.service";
import {Cart} from "../../models/cart";
import {AdminComponent} from "../admin/admin.component";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  public products: Product[] = [];
  public category: string = '';

  constructor(private productService: ProductService) {}

  filterByCategory(cat: string){
    this.category = cat;
  }

  ngOnInit() {
    this.productService.index().subscribe((data) => {
      this.products = data;
    });
  }

  protected readonly AdminComponent = AdminComponent;
}
