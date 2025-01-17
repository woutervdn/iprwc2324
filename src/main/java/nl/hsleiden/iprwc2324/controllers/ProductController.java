package nl.hsleiden.iprwc2324.controllers;

import nl.hsleiden.iprwc2324.models.Category;
import nl.hsleiden.iprwc2324.models.Product;
import nl.hsleiden.iprwc2324.repositories.CategoryRepository;
import nl.hsleiden.iprwc2324.repositories.ProductRepository;
import nl.hsleiden.iprwc2324.requests.ProductRequest;
import nl.hsleiden.iprwc2324.responses.ProductResponse;
import nl.hsleiden.iprwc2324.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path = "/api/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> productIndex() {

        List<ProductResponse> response = new ArrayList<>();

        Iterable<Product> products = productRepository.findAll();

        for (Product p: products) {
            String category = categoryRepository.findById(p.getCategoryId()).get().getName();

            ProductResponse res = new ProductResponse(
                    p.getId(),
                    p.getTitle(),
                    p.getImage(),
                    p.getPrice(),
                    p.getDescription(),
                    category
            );
            response.add(res);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> productCreate(@RequestBody ProductRequest product, @RequestHeader("Authorization") String token) {
        System.out.println("Token: " + token);
        if (!this.authService.isAuthenticated(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Product p = new Product();

        if (product.title.isEmpty() || product.description.isEmpty() || product.category.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Category> category = categoryRepository.findByName(product.category);

        if (category.isEmpty()) {
            Category newCat = new Category();
            newCat.setName(product.category);
            p.setCategoryId(categoryRepository.save(newCat).getId());
        } else {
            p.setCategoryId(category.get().getId());
        }

        p.setTitle(product.title);
        p.setDescription(product.description);
        p.setImage(product.image);
        p.setPrice(product.price);

        return new ResponseEntity<>(productRepository.save(p), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> productRead(@PathVariable Long id) {
        Optional<Product> prod = productRepository.findById(id);

        if (prod.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(prod.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> productUpdate(@RequestBody ProductRequest product) {
        Optional<Product> prod = productRepository.findById(product.id.get());

        if (prod.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product p = prod.get();

        Optional<Category> category = categoryRepository.findByName(product.category);

        if (category.isEmpty()) {
            Category newCat = new Category();
            newCat.setName(product.category);
            p.setCategoryId(categoryRepository.save(newCat).getId());
        } else {
            p.setCategoryId(category.get().getId());
        }

        p.setTitle(product.title);
        p.setDescription(product.description);
        p.setImage(product.image);
        p.setPrice(product.price);

        return new ResponseEntity<>(productRepository.save(p), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> productDelete(@PathVariable Long id) {
        Optional<Product> prod = productRepository.findById(id);

        if (prod.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productRepository.delete(prod.get());

        return new ResponseEntity<>(prod.get(), HttpStatus.OK);
    }

}
