package nl.hsleiden.iprwc2324.repositories;

import nl.hsleiden.iprwc2324.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User getUserByUsername(String username);

}
