package nl.hsleiden.iprwc2324.controllers;

import lombok.extern.java.Log;
import nl.hsleiden.iprwc2324.models.Cart;
import nl.hsleiden.iprwc2324.models.User;
import nl.hsleiden.iprwc2324.repositories.CartRepository;
import nl.hsleiden.iprwc2324.repositories.UserRepository;
import nl.hsleiden.iprwc2324.requests.LoginRequest;
import nl.hsleiden.iprwc2324.requests.getUserRequest;
import nl.hsleiden.iprwc2324.responses.LoginResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.coyote.Response;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest user){
        if (userRepository.getUserByUsername(user.getUsername()) == null) {
            return new ResponseEntity<>(new LoginResponse("User not Found", false), HttpStatus.OK);
        }
        User validUser = userRepository.getUserByUsername(user.getUsername());

        if (!encoder.matches(user.getPassword(), validUser.getPassword())) {
            return new ResponseEntity<>(new LoginResponse("Invalid Password", false), HttpStatus.OK);
        }

        validUser.setToken(RandomStringUtils.randomAlphanumeric(128));
        validUser.setExpirationDate(new Date(System.currentTimeMillis() + 3600 * 1000));

        userRepository.save(validUser);

        return new ResponseEntity<>(new LoginResponse("Logged In", true, validUser.getToken()), HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody User createUser(@RequestBody LoginRequest user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setToken(RandomStringUtils.randomAlphanumeric(128));
        newUser.setExpirationDate(new Date(System.currentTimeMillis() + 3600 * 1000));

        userRepository.save(newUser);

        Cart cart = new Cart();
        cart.setUser(newUser);
        cartRepository.save(cart);

        return newUser;
    }

    @PostMapping("/checkalive")
    public ResponseEntity<LoginResponse> checkAlive(@RequestBody String token) {
        Optional<User> user = userRepository.getUserByToken(token);
        if (user.isEmpty()) {
            return new ResponseEntity<>(new LoginResponse("User not found", false),HttpStatus.OK);
        }

        User validUser = user.get();

        if (validUser.getExpirationDate().before(new Date())) {
            return new ResponseEntity<>(new LoginResponse("Token Expired", false),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new LoginResponse("Valid Token", true),HttpStatus.OK);
        }
    }

}
