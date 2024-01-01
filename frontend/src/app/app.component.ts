import {Component, OnInit} from '@angular/core';
import { Product } from '../models/product';
import {ProductService} from "./product.service";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'IPRWC';
}
