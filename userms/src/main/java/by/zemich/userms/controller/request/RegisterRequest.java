package by.zemich.userms.controller.request;

import by.zemich.userms.dao.entity.Role;
import lombok.Data;


@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
}
