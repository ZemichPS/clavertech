package by.zemich.newsms.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;

import java.security.Provider;

@Configuration
@EnableWebSecurity
@Profile(value = "prod")
public class SecurityConfig {

    AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider){
        return new ProviderManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        return new JwtAuthenticationProvider();
    }











}

