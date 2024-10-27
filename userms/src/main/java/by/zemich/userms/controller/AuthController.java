package by.zemich.userms.controller;

import by.zemich.userms.controller.request.AuthLoginPasswordRequest;
import by.zemich.userms.controller.request.RegisterRequest;
import by.zemich.userms.dao.entity.User;
import by.zemich.userms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        User registered = authService.register(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registered.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<String> authenticate(@RequestParam String login,
                                               @RequestParam String password
    ) {
        AuthLoginPasswordRequest request = AuthLoginPasswordRequest.builder()
                .login(login)
                .password(password)
                .build();
        String jwt = authService.authenticate(request);
        return ResponseEntity.ok(jwt);
    }

}
