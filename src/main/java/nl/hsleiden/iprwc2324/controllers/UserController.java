package nl.hsleiden.iprwc2324.controllers;

import lombok.extern.java.Log;
import nl.hsleiden.iprwc2324.models.Cart;
import nl.hsleiden.iprwc2324.models.User;
import nl.hsleiden.iprwc2324.repositories.CartRepository;
import nl.hsleiden.iprwc2324.repositories.UserRepository;
import nl.hsleiden.iprwc2324.requests.LoginRequest;
import nl.hsleiden.iprwc2324.requests.getUserRequest;
import nl.hsleiden.iprwc2324.responses.LoginResponse;
import nl.hsleiden.iprwc2324.responses.UserResponse;
import nl.hsleiden.iprwc2324.services.AuthService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.coyote.Response;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private AuthService authService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping
    public ResponseEntity<Object> getAllUsers(@RequestHeader("Authorization") String token) {
        if (!authService.isAuthenticatedAndAdmin(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<UserResponse> response = new ArrayList<>();

        for (User user: userRepository.findAll()) {
            UserResponse res = new UserResponse(user);
            response.add(res);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader("Authorization") String token) {
        if (!authService.isAuthenticated(token)) {
            return new ResponseEntity<>("Invalid Token", HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userRepository.getUserByToken(token);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User not Found", HttpStatus.NOT_FOUND);
        }

        User validUser = user.get();
        validUser.setToken(RandomStringUtils.randomAlphanumeric(128));
        userRepository.save(validUser);

        return new ResponseEntity<>(HttpStatus.OK);
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

        return new ResponseEntity<>(new LoginResponse("Logged In", true, validUser.isAdmin(), validUser.getToken()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody LoginRequest user) {
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());

        if (user.getPassword().isEmpty() || user.getUsername().isEmpty()) {
            return new ResponseEntity<>("Username or password empty", HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setToken(RandomStringUtils.randomAlphanumeric(128));
        newUser.setExpirationDate(new Date(System.currentTimeMillis() + 3600 * 1000));

        userRepository.save(newUser);

        Cart cart = new Cart();
        cart.setUser(newUser);
        cartRepository.save(cart);

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

}
