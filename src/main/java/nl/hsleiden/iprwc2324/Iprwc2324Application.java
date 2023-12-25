package nl.hsleiden.iprwc2324;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Iprwc2324Application {

    public static void main(String[] args) {
        SpringApplication.run(Iprwc2324Application.class, args);
    }

}
