package nl.hsleiden.iprwc2324.requests;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.hsleiden.iprwc2324.models.Product;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {

    private Integer amount;

    private Product product;

}
