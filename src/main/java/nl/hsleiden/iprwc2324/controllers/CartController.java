package nl.hsleiden.iprwc2324.controllers;

import nl.hsleiden.iprwc2324.models.*;
import nl.hsleiden.iprwc2324.repositories.CartRepository;
import nl.hsleiden.iprwc2324.repositories.UserRepository;
import nl.hsleiden.iprwc2324.requests.ProductRequest;
import nl.hsleiden.iprwc2324.responses.CartResponse;
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
@RequestMapping(path = "/api/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

//    @PostMapping
//    public ResponseEntity<Cart> cartCreate(@RequestBody cartRequest cart) {
//        Cart p = new Cart();
//
//        Optional<Category> category = categoryRepository.findByName(cart.category);
//
//        if (category.isEmpty()) {
//            Category newCat = new Category();
//            newCat.setName(cart.category);
//            p.setCategoryId(categoryRepository.save(newCat).getId());
//        } else {
//            p.setCategoryId(category.get().getId());
//        }
//
//        p.setTitle(cart.title);
//        p.setDescription(cart.description);
//        p.setImage(cart.image);
//        p.setPrice(cart.price);
//
//        return new ResponseEntity<>(cartRepository.save(p), HttpStatus.OK);
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponse> cartRead(@PathVariable long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if (cart.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart validCart = cart.get();

        CartResponse response = new CartResponse(validCart.getId(), validCart.getUser().getId(), validCart.getItems(), validCart.getTotal());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Cart> cartUpdate(@PathVariable Long userId, @RequestBody List<CartItem> items) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);

        if (cart.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart validCart = cart.get();
        validCart.setItems(items);
        BigDecimal total = BigDecimal.valueOf(0);

        for (CartItem item: items) {
            total = total.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getAmount())));
        }

        validCart.setTotal(total);

        return new ResponseEntity<>(validCart, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Cart> cartDelete(@PathVariable long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);

        if (cart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart validCart = cart.get();

        validCart.setItems(new ArrayList<>());
        validCart.setTotal(BigDecimal.valueOf(0));

        cartRepository.save(validCart);

        return new ResponseEntity<>(validCart, HttpStatus.OK);
    }

}
