package nl.hsleiden.iprwc2324.services;

import nl.hsleiden.iprwc2324.models.Category;
import nl.hsleiden.iprwc2324.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

}
