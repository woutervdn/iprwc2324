package nl.hsleiden.iprwc2324.seeder;

import nl.hsleiden.iprwc2324.models.*;
import nl.hsleiden.iprwc2324.repositories.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductOrderItemRepository productOrderItemRepository;

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

        Category none = new Category("Geen");
        Category heren = new Category("Heren");
        Category dames = new Category("Dames");
        Category actie = new Category("Actie");
        categoryRepository.saveAll(Arrays.asList(heren,dames,actie,none));

        Product prod1 = new Product("Lange Broek", "/assets/jeans.jpg", BigDecimal.valueOf(54.99), "Gescheurde spijkerbroek", heren.getId());
        Product prod2 = new Product("Korte Rok", "/assets/skirt.jpeg", BigDecimal.valueOf(24.99), "Korte denim rok", dames.getId());
        Product prod3 = new Product("Shirt", "/assets/shirt.jpg", BigDecimal.valueOf(64.99), "Luchtig shirt voor in de zomer", actie.getId());
        Product prod4 = new Product("Trui", "/assets/sweater.jpeg", BigDecimal.valueOf(49.99), "Dikke trui voor jongeren", heren.getId());
        Product prod5 = new Product("Zomer Jurk", "/assets/dress.jpg", BigDecimal.valueOf(32.99), "Luchtige zomer jurk voor het strand", dames.getId());
        Product prod6 = new Product("Winterjas", "/assets/coat.jpg", BigDecimal.valueOf(44.99), "Bruine modieuze winterjas voor mannen", actie.getId());
        Product prod7 = new Product("Overall", "/assets/overall.jpg", BigDecimal.valueOf(54.99), "Bruine stoffen overall", actie.getId());
        Product prod8 = new Product("Cargo Broek", "/assets/cargo.jpg", BigDecimal.valueOf(44.99), "Zwarte syntetische cargo broek", heren.getId());
        Product prod9 = new Product("Bruine Zonnebril", "/assets/sunglasses.jpg", BigDecimal.valueOf(19.99), "Bruine stylistische zonnebril", dames.getId());
        Product prod10 = new Product("Wit Overhemd", "/assets/white_shirt.jpg", BigDecimal.valueOf(79.99), "Professioneel wit overhemd", heren.getId());
        productRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10));

//        ProductOrderItem item1 = new ProductOrderItem(prod1, 2);
//        ProductOrderItem item2 = new ProductOrderItem(prod2, 3);
//        List<ProductOrderItem> items = new ArrayList<>(Arrays.asList(item1, item2));
//        productOrderItemRepository.saveAll(items);
//
//        ProductOrder order1 = new ProductOrder(user, items, BigDecimal.valueOf(184.95));
//        productOrderRepository.save(order1);
    }

}
