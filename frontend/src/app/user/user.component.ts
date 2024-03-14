import {Component, Input} from '@angular/core';
import {Order} from "../../models/order";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  @Input() user: any = { id: 0, username: '', isAdmin: false }


  protected readonly Date = Date;
  protected readonly formatDate = formatDate;
}
