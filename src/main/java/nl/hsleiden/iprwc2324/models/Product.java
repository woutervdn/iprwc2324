package nl.hsleiden.iprwc2324.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private Long id;

    @Setter
    @Nonnull
    private String title;

    @Setter
    private String image;

    @Setter
    @Nonnull
    private BigDecimal price;

    @Setter
    @Nonnull
    private String description;

    @Setter
    @Nonnull
    private String category;

}
