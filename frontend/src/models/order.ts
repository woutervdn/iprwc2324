import {OrderItem} from "./order_item";

export interface Order {
  id: number,
  items: OrderItem[]
  total: number
}
