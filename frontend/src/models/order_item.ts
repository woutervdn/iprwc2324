import {OrderItemProduct} from "./order_item_product";

export interface OrderItem {
  id: number,
  product: OrderItemProduct,
  amount: number
}
