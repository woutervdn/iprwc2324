package nl.hsleiden.iprwc2324.services;

import nl.hsleiden.iprwc2324.models.Category;
import nl.hsleiden.iprwc2324.repositories.CategoryRepository;
import nl.hsleiden.iprwc2324.requests.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> index() {
        return categoryRepository.findAll();
    }

    public Category create(String category){
        Category cat = new Category(category);
        categoryRepository.save(cat);

        return cat;
    }

    public Optional<Category> read(Long id){
        return categoryRepository.findById(id);
    }

    public void update(CategoryRequest category){
        Optional<Category> cat = categoryRepository.findById(category.getId());
        if (cat.isEmpty()){
            this.create(category.getName());
            return;
        }

        Category validCategory = cat.get();
        validCategory.setName(category.getName());
        categoryRepository.save(validCategory);
    }

    public void delete(Long id) {
        Optional<Category> cat = categoryRepository.findById(id);
        if (cat.isEmpty()){
            return;
        }

        Category validCategory = cat.get();
        categoryRepository.delete(validCategory);
    }

}
