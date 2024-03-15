package nl.hsleiden.iprwc2324.repositories;

import nl.hsleiden.iprwc2324.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> getProductsByCategoryId(Long Id);

}
