package nl.hsleiden.iprwc2324.responses;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.hsleiden.iprwc2324.models.CartItem;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartResponse {

    private Long Id;

    private Long user_id;

    private List<CartItem> items;

    private BigDecimal total;

}
