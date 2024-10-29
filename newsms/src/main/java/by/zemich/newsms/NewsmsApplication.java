package by.zemich.newsms;

import by.zemich.newsms.api.config.properties.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({JWTProperty.class})
@EnableFeignClients
public class NewsmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsmsApplication.class, args);
    }

}
