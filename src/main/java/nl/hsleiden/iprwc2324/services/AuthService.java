package nl.hsleiden.iprwc2324.services;

import nl.hsleiden.iprwc2324.models.User;
import nl.hsleiden.iprwc2324.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public Boolean isAuthenticatedAndAdmin(String token) {
        if (token == null){
            return false;
        }

        Optional<User> user = userRepository.getUserByToken(token);
        if (user.isEmpty()){
            return false;
        }

        return user.get().isAdmin();
    }

    public Boolean isAuthenticated(String token) {
        if (token == null){
            return false;
        }

        Optional<User> user = userRepository.getUserByToken(token);
        if (user.isEmpty()){
            return false;
        }

        return true;
    }


}
