package nl.hsleiden.iprwc2324.seeder;

import nl.hsleiden.iprwc2324.models.Cart;
import nl.hsleiden.iprwc2324.models.Category;
import nl.hsleiden.iprwc2324.models.Product;
import nl.hsleiden.iprwc2324.models.User;
import nl.hsleiden.iprwc2324.repositories.CartRepository;
import nl.hsleiden.iprwc2324.repositories.CategoryRepository;
import nl.hsleiden.iprwc2324.repositories.ProductRepository;
import nl.hsleiden.iprwc2324.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class DataSeeder implements CommandLineRunner {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(encoder.encode("test1234"));
        user.setToken(RandomStringUtils.randomAlphanumeric(128));
        user.setAdmin(true);

        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        Category heren = new Category("Heren");
        Category dames = new Category("Dames");
        Category actie = new Category("Actie");
        categoryRepository.saveAll(Arrays.asList(heren,dames,actie));

        Product prod1 = new Product("Lange Broek", "/assets/jeans.jpg", BigDecimal.valueOf(54.99), "Gescheurde spijkerbroek", heren.getId());
        Product prod2 = new Product("Korte Rok", "/assets/skirt.jpeg", BigDecimal.valueOf(24.99), "Korte denim rok", dames.getId());
        Product prod3 = new Product("Shirt", "/assets/shirt.jpg", BigDecimal.valueOf(64.99), "Warm vest voor de winterdagen", actie.getId());
        Product prod4 = new Product("Trui", "/assets/sweater.jpeg", BigDecimal.valueOf(49.99), "Dikke trui voor jongeren", heren.getId());
        Product prod5 = new Product("Zomer Jurk", "/assets/dress.jpg", BigDecimal.valueOf(32.99), "Luchtige zomer jurk voor het strand", dames.getId());
        Product prod6 = new Product("Winterjas", "/assets/coat.jpg", BigDecimal.valueOf(44.99), "Bruine modieuze winterjas voor mannen", actie.getId());
        productRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6));
    }

}
