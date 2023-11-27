package nl.hsleiden.iprwc2324.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private Long id;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String image;

    @Setter
    @Getter
    private BigDecimal price;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private String category;

}
