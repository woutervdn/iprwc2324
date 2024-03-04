package nl.hsleiden.iprwc2324.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    public Product(@Nonnull String title, String image, @Nonnull BigDecimal price, @Nonnull String description, @Nonnull Long categoryId) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }


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
    private Long categoryId;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
