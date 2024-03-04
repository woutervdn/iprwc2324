package nl.hsleiden.iprwc2324.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;

    private String title;

    private String image;

    private BigDecimal price;

    private String description;

    private String category;
}
