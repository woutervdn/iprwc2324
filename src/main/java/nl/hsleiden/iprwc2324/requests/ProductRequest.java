package nl.hsleiden.iprwc2324.requests;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class ProductRequest {

    public Optional<UUID> id;

    public String title;

    public String image;

    public BigDecimal price;

    public String description;

    public String category;

}
