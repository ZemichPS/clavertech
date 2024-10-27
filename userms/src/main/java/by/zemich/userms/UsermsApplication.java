package by.zemich.userms;

import by.zemich.userms.config.SecureProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication

@EnableConfigurationProperties({SecureProperties.class})
public class UsermsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsermsApplication.class, args);
    }

}

