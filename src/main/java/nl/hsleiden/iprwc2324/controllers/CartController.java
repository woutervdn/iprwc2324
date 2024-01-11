package nl.hsleiden.iprwc2324.controllers;

import nl.hsleiden.iprwc2324.models.*;
import nl.hsleiden.iprwc2324.repositories.*;
import nl.hsleiden.iprwc2324.requests.CartItemRequest;
import nl.hsleiden.iprwc2324.requests.CartRequest;
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
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

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
    public ResponseEntity<Cart> cartUpdate(@PathVariable Long userId, @RequestBody CartRequest cartRequest) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);

        if (cart.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart validCart = cart.get();
        List<CartItem> oldItems = validCart.getItems();
        if (!oldItems.isEmpty()) {
            validCart.setItems(new ArrayList<>());
            cartRepository.save(validCart);
            cartItemRepository.deleteAll(oldItems);
        }



        List<CartItem> items = new ArrayList<>();

        for (CartItemRequest itemRequest: cartRequest.getItems()) {
            System.out.println(itemRequest.getProduct());
            Optional<Product> product = productRepository.findById(itemRequest.getProduct());

            if (product.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Product validProduct = product.get();

            CartItem item = new CartItem(validProduct, itemRequest.getAmount());
            cartItemRepository.save(item);

            items.add(item);
        }

        validCart.setItems(items);
        BigDecimal total = BigDecimal.valueOf(0);

        for (CartItem item: items) {
            total = total.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getAmount())));
        }

        validCart.setTotal(total);

        cartRepository.save(validCart);

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
