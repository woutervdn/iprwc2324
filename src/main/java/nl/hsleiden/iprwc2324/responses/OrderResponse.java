package nl.hsleiden.iprwc2324.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.hsleiden.iprwc2324.models.ProductOrderItem;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderResponse {

    private Long Id;

    private Long user_id;

    private List<ProductOrderItem> items;

    private BigDecimal total;

}
