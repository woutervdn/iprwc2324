package nl.hsleiden.iprwc2324.repositories;

import nl.hsleiden.iprwc2324.models.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
