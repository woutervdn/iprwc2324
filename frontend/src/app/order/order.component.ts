import {Component, Input} from '@angular/core';
import {Order} from "../../models/order";
import {ActivatedRoute} from "@angular/router";
import {OrderService} from "../order.service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent {

  @Input() order: Order = { id: 0, items: [], total: 0 }

  @Input() route: String = '';

  constructor(private orderService: OrderService) {
  }

  delete() {
    this.orderService
  }

}
