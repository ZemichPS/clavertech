package by.zemich.userms.service;

import by.zemich.userms.controller.request.AuthLoginPasswordRequest;
import by.zemich.userms.controller.request.RegisterRequest;
import by.zemich.userms.dao.entity.Role;
import by.zemich.userms.dao.entity.User;
import by.zemich.userms.service.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public String authenticate(String login, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                login, password
        );

        Authentication authenticationResult = authenticationManager.authenticate(authentication);
        if (!authenticationResult.isAuthenticated()) {
            throw new BadCredentialsException("Bad credentials");
        }
        return getToken(login);
    }


    private String getToken(String userName) {
        User user = userService.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(
                "User with email %s is nowhere to be found".formatted(userName))
        );
        return tokenService.generate(user);
    }


}
