package nl.hsleiden.iprwc2324.repositories;

import nl.hsleiden.iprwc2324.models.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long UserId);

}
