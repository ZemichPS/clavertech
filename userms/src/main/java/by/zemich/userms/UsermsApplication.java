package by.zemich.userms;

import by.zemich.userms.config.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication

@EnableConfigurationProperties({JWTProperty.class})
public class UsermsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsermsApplication.class, args);
    }

}

