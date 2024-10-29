package by.zemich.newsms.core.service;

import by.zemich.newsms.core.domain.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userFeignClient.getUserByUsername(username);
        return User.builder()
                .username(userDTO.getUsername())
                .roles(String.valueOf(userDTO.getRole()))
                .build();
    }
}
