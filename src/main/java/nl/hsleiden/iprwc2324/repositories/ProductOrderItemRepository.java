package nl.hsleiden.iprwc2324.repositories;

import nl.hsleiden.iprwc2324.models.ProductOrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductOrderItemRepository extends CrudRepository<ProductOrderItem, Long> {

    Optional<ProductOrderItem> findTopByOrderById();

}
