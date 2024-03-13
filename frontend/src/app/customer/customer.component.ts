import {Component, OnInit} from '@angular/core';
import {Order} from "../../models/order";
import {OrderService} from "../order.service";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  public orders: Order[] = [];

  constructor(
    private orderService: OrderService
  ) {}

  ngOnInit(): void {
    this.orderService.index().subscribe(orders => {
      this.orders = orders;
    });
  }

}
