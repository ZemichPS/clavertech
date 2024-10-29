package by.zemich.newsms.api.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface JwtDecoder {

    UserDetails getUserDetails();

    List<GrantedAuthority> getAuthorities();


}
