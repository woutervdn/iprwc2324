package nl.hsleiden.iprwc2324.repositories;

import nl.hsleiden.iprwc2324.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User getUserByUsername(String username);

    Optional<User> getUserByToken(String token);

}
