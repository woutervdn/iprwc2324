package nl.hsleiden.iprwc2324.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import nl.hsleiden.iprwc2324.models.Product;
import nl.hsleiden.iprwc2324.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<Iterable<Product>> productIndex() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Product> productCreate(@RequestBody Product product) {
        Product p = new Product();

        p.setTitle(product.getTitle());
        p.setDescription(product.getDescription());
        p.setImage(product.getImage());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());

        return new ResponseEntity<>(productRepository.save(p), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> productRead(@PathVariable long id) {
        Optional<Product> prod = productRepository.findById(id);

        if (prod.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(prod.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> productUpdate(@RequestBody Product product) {
        Optional<Product> prod = productRepository.findById(product.getId());

        if (prod.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product p = prod.get();

        p.setTitle(product.getTitle());
        p.setDescription(product.getDescription());
        p.setImage(product.getImage());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());

        return new ResponseEntity<>(productRepository.save(p), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> productDelete(@PathVariable long id) {
        Optional<Product> prod = productRepository.findById(id);

        if (prod.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productRepository.delete(prod.get());

        return new ResponseEntity<>(prod.get(), HttpStatus.OK);
    }

}
