package by.zemich.userms.security.service;

import by.zemich.userms.dao.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class UserAdminOrAddressOwnerAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final UserRepository userRepository;


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext request) {
        UUID userId = UUID.fromString(request.getRequest().getParameter("userId"));

        if (authentication.get().getAuthorities().stream().anyMatch(g -> g.getAuthority().equals("ROLE_ADMIN"))) {
            return new AuthorizationDecision(true);
        }

        // TODO заменить EXCEPTION
        boolean isAuthorized = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("sdf")).getUsername()
                .equalsIgnoreCase(authentication.get().getName());

        if (isAuthorized) {
            return new AuthorizationDecision(true);
        }

        return new AuthorizationDecision(false);
    }

}
