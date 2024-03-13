package nl.hsleiden.iprwc2324.controllers;

import nl.hsleiden.iprwc2324.models.Product;
import nl.hsleiden.iprwc2324.models.ProductOrder;
import nl.hsleiden.iprwc2324.models.ProductOrderItem;
import nl.hsleiden.iprwc2324.models.User;
import nl.hsleiden.iprwc2324.repositories.*;
import nl.hsleiden.iprwc2324.requests.OrderItemRequest;
import nl.hsleiden.iprwc2324.requests.OrderRequest;
import nl.hsleiden.iprwc2324.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(path = "/api/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductOrderItemRepository productOrderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthService authService;

    @GetMapping()
    public ResponseEntity<Object> orderIndex(@RequestHeader("Authorization") String token) {
        if (!authService.isAuthenticated(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (authService.isAuthenticatedAndAdmin(token)) {
            return new ResponseEntity<>(productOrderRepository.findAll(), HttpStatus.OK);
        }

        return new ResponseEntity<>(productOrderRepository.getProductOrderByUserToken(token), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> orderRead(@PathVariable Long orderId) {
        Optional<ProductOrder> order = productOrderRepository.findById(orderId);

        if (!order.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> orderCreate(@RequestHeader("Authorization") String token, @RequestHeader@RequestBody OrderRequest request) {
        if (!authService.isAuthenticated(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<User> user = userRepository.getUserByToken(token);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User not Found", HttpStatus.BAD_REQUEST);
        }

        User validUser = user.get();

        ProductOrder order = new ProductOrder();
        BigDecimal total = BigDecimal.valueOf(0);
        List<ProductOrderItem> items = new ArrayList<>();

        for (OrderItemRequest item :request.getItems()) {
            Optional<Product> product = productRepository.findById(item.getProduct().getId());

            if (product.isEmpty()) {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
            ProductOrderItem orderItem = new ProductOrderItem();

            Product prod = product.get();

            total = total.add(order.getTotal().add(prod.getPrice().multiply(BigDecimal.valueOf(item.getAmount()))));

            orderItem.setProduct(prod);
            orderItem.setAmount(item.getAmount());

            items.add(productOrderItemRepository.save(orderItem));
        }

        order.setTotal(total);
        order.setItems(items);
        order.setUser(validUser);

        productOrderRepository.save(order);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderID}")
    public ResponseEntity<Object> orderUpdate(@PathVariable("orderID") Long orderID, @RequestBody OrderRequest orderRequest) {
        Optional<ProductOrder> productOrder = productOrderRepository.findById(orderID);

        if (productOrder.isEmpty()) {
            return new ResponseEntity<>("Order not Found", HttpStatus.NOT_FOUND);
        }

        ProductOrder order = productOrder.get();

        List<ProductOrderItem> oldItems = order.getItems();

        BigDecimal total = BigDecimal.valueOf(0);
        List<ProductOrderItem> items = new ArrayList<>();

        for (OrderItemRequest item :orderRequest.getItems()) {
            Optional<Product> product = productRepository.findById(item.getProduct().getId());

            if (product.isEmpty()) {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
            ProductOrderItem orderItem = new ProductOrderItem();

            Product prod = product.get();

            total = total.add(prod.getPrice().multiply(BigDecimal.valueOf(item.getAmount())));

            orderItem.setProduct(prod);
            orderItem.setAmount(item.getAmount());

            items.add(productOrderItemRepository.save(orderItem));
        }

        order.setTotal(total);
        order.setItems(items);

        productOrderItemRepository.deleteAll(oldItems);

        productOrderRepository.save(order);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderID}")
    public ResponseEntity<Object> orderDelete(@PathVariable Long orderID) {
        Optional<ProductOrder> productOrder = productOrderRepository.findById(orderID);

        if (productOrder.isEmpty()) {
            return new ResponseEntity<>("Order not Found", HttpStatus.NOT_FOUND);
        }

        ProductOrder order = productOrder.get();

        productOrderRepository.delete(order);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
