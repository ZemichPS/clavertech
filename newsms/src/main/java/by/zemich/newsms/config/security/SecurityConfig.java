package by.zemich.newsms.config.security;

import by.zemich.newsms.config.properties.JWTProperty;
import by.zemich.newsms.config.security.filter.JWTFilter;
import by.zemich.newsms.core.service.JWTHandler;
import by.zemich.newsms.core.service.UserDetailsServiceImpl;
import by.zemich.newsms.core.service.UserFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@Profile(value = "prod")
public class SecurityConfig {

    @Bean
    public JWTHandler jwtHandler(JWTProperty jwtProperty) {
        return new JWTHandler(jwtProperty);
    }

    @Bean
    public UserDetailsService userDetailsService(UserFeignClient userFeignClient) {
        return new UserDetailsServiceImpl(userFeignClient);
    }

    @Bean
    public JWTFilter jwtFilter(JWTHandler jwtHandler, UserDetailsService userDetailsService) {
        return new JWTFilter(jwtHandler, userDetailsService);
    }


}

