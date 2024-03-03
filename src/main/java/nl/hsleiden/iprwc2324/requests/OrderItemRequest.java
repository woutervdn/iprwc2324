package nl.hsleiden.iprwc2324.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.hsleiden.iprwc2324.models.Product;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    private Integer amount;

    private Product product;

}
