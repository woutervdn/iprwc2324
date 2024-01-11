package nl.hsleiden.iprwc2324.controllers;

import nl.hsleiden.iprwc2324.models.Cart;
import nl.hsleiden.iprwc2324.models.User;
import nl.hsleiden.iprwc2324.repositories.CartRepository;
import nl.hsleiden.iprwc2324.repositories.UserRepository;
import nl.hsleiden.iprwc2324.requests.getUserRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/user")
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
    public @ResponseBody boolean loginUser(@RequestBody User user){
        if (userRepository.getUserByUsername(user.getUsername()) == null) {
            return false;
        }
        User validUser = userRepository.getUserByUsername(user.getUsername());

        if (!encoder.matches(user.getPassword(), validUser.getPassword())) {
            return false;
        }

        validUser.setToken(RandomStringUtils.randomAlphanumeric(128));

        userRepository.save(validUser);

        return true;
    }

    @PostMapping
    public @ResponseBody User createUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setToken(RandomStringUtils.randomAlphanumeric(128));

        userRepository.save(newUser);

        Cart cart = new Cart();
        cart.setUser(newUser);
        cartRepository.save(cart);

        return newUser;
    }

}
