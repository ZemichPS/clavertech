package by.zemich.userms.service;

import by.zemich.userms.controller.request.AuthLoginPasswordRequest;
import by.zemich.userms.controller.request.RegisterRequest;
import by.zemich.userms.dao.entity.Role;
import by.zemich.userms.dao.entity.User;
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
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public String authenticate(AuthLoginPasswordRequest loginPasswordRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginPasswordRequest.getLogin(), loginPasswordRequest.getPassword()
        );
        Authentication authenticationResult = authenticationManager.authenticate(authentication);
        if (!authenticationResult.isAuthenticated()) {
            throw new BadCredentialsException("Bad credentials");
        }
        return getToken(loginPasswordRequest.getLogin());
    }

    public User register(RegisterRequest registerRequest) {
        User rigisterUser = User.builder()
                .registerAt(LocalDateTime.now())
                .username(registerRequest.getUsername())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.valueOf(registerRequest.getRole().toUpperCase()))
                .build();

        return userService.save(rigisterUser);
    }

    String getToken(String userName) {
        User user = userService.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(
                "User with username %s is nowhere to be found".formatted(userName))
        );
        return tokenService.generate(user);
    }


}
