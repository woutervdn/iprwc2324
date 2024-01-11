package nl.hsleiden.iprwc2324.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    private CartItemRequest[] items;

}
