import {CartItem} from "./cart_item";

export interface Cart {
  id: number,
  user_id: number,
  items: CartItem[],
  total: number
}
