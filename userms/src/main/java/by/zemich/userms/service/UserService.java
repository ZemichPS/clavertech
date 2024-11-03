package by.zemich.userms.service;

import by.zemich.userms.controller.request.RegisterRequest;
import by.zemich.userms.controller.response.UserFullResponse;
import by.zemich.userms.dao.entity.Role;
import by.zemich.userms.dao.entity.User;
import by.zemich.userms.dao.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConversionService conversionService;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User register(@Validated RegisterRequest registerRequest) {
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User rigisterUser = conversionService.convert(registerRequest, User.class);
        return userRepository.save(rigisterUser);
    }


    public UserFullResponse deactivate(UUID userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setActive(false);
                    return userRepository.save(user);
                }).map(user -> conversionService.convert(user, UserFullResponse.class))
                .orElseThrow(
                        () -> new EntityNotFoundException("User with id %s is nowhere to be found".formatted(userId))
                );
    }

    public Page<UserFullResponse> findAll(Pageable pageable) {
        List<UserFullResponse> userFullResponses = userRepository.findAll(pageable).getContent().stream()
                .map(user -> conversionService.convert(user, UserFullResponse.class))
                .toList();
        return new PageImpl<>(userFullResponses, pageable, userFullResponses.size());
    }

    public UserFullResponse findById(UUID userId) {
        return userRepository.findById(userId)
                .map(user -> conversionService.convert(user, UserFullResponse.class))
                .orElseThrow(
                        () -> new EntityNotFoundException("User with id %s is nowhere to be found".formatted(userId))
                );
    }
}
