package nl.hsleiden.iprwc2324.controllers;

import nl.hsleiden.iprwc2324.models.*;
import nl.hsleiden.iprwc2324.repositories.*;
import nl.hsleiden.iprwc2324.requests.CategoryRequest;
import nl.hsleiden.iprwc2324.requests.OrderItemRequest;
import nl.hsleiden.iprwc2324.requests.OrderRequest;
import nl.hsleiden.iprwc2324.responses.OrderResponse;
import nl.hsleiden.iprwc2324.services.AuthService;
import nl.hsleiden.iprwc2324.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping()
    public ResponseEntity<Object> categoryIndex() {
        return new ResponseEntity<>(categoryService.index(), HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<Object> orderRead(@PathVariable Long catId) {
        Optional<Category> category = categoryService.read(catId);
        if (category.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Category cat = category.get();

        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> categoryCreate(@RequestHeader("Authorization") String token, @RequestBody CategoryRequest request) {
        if (!authService.isAuthenticatedAndAdmin(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(categoryService.create(request.getName()), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Object> categoryUpdate(@RequestHeader("Authorization") String token, @RequestBody CategoryRequest request) {
        if (!authService.isAuthenticatedAndAdmin(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        categoryService.update(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<Object> orderDelete(@RequestHeader("Authorization") String token, @PathVariable Long catId) {
        if (!authService.isAuthenticatedAndAdmin(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        categoryService.delete(catId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
