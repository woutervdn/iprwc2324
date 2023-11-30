package nl.hsleiden.iprwc2324.controllers;

import nl.hsleiden.iprwc2324.models.User;
import nl.hsleiden.iprwc2324.repositories.UserRepository;
import nl.hsleiden.iprwc2324.requests.getUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public @ResponseBody User getUserByUsername(@RequestBody getUserRequest request) {
        return userRepository.getUserByUsername(request.username);
    }

//    @PostMapping
//    public @ResponseBody User createUser(@RequestBody User user) {
//        User newUser = new User();
//
//
//    }

}
