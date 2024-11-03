package by.zemich.userms.service.converters;

import by.zemich.userms.controller.request.RegisterRequest;
import by.zemich.userms.dao.entity.Role;
import by.zemich.userms.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

public class RegisterRequestToUserConverter implements Converter<RegisterRequest, User> {
    @Override
    public User convert(RegisterRequest registerRequest) {
        return User.builder()
                .registerAt(LocalDateTime.now())
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .role(Role.valueOf(registerRequest.getRole().toUpperCase()))
                .password(registerRequest.getPassword())
                .active(true)
                .build();
    }
}
