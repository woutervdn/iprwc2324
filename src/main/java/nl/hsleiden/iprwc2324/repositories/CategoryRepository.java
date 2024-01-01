package nl.hsleiden.iprwc2324.repositories;

import nl.hsleiden.iprwc2324.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByName(String name);

}
