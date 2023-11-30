package nl.hsleiden.iprwc2324.repositories;

import nl.hsleiden.iprwc2324.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {}
