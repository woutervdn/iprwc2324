package nl.hsleiden.iprwc2324.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import nl.hsleiden.iprwc2324.models.Product;
import nl.hsleiden.iprwc2324.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public @ResponseBody Iterable<Product> productIndex() {
        return productRepository.findAll();
    }

    @PostMapping
    public @ResponseBody Product productCreate(@RequestBody Product product) {
        Product p = new Product();

        p.setTitle(product.getTitle());
        p.setDescription(product.getDescription());
        p.setImage(product.getImage());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());

        productRepository.save(p);

        return p;
    }

}
