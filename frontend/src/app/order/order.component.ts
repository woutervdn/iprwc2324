import {Component, Input} from '@angular/core';
import {Order} from "../../models/order";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent {

  @Input() order: Order = { id: 0, items: [], total: 0 }

}
