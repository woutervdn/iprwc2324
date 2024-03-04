package nl.hsleiden.iprwc2324.repositories;

import nl.hsleiden.iprwc2324.models.ProductOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {

}
