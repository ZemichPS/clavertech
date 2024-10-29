package by.zemich.newsms.api.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;

    public JWTAuthenticationProvider(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        authentication.
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
