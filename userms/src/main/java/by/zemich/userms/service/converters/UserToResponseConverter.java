package by.zemich.userms.service.converters;

import by.zemich.userms.controller.response.UserFullResponse;
import by.zemich.userms.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserToResponseConverter implements Converter<User, UserFullResponse> {
    @Override
    public UserFullResponse convert(User user) {
        return UserFullResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole().name())
                .active(user.isActive())
                .registerAt(user.getRegisterAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
