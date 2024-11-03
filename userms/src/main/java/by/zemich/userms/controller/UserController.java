package by.zemich.userms.controller;

import by.zemich.userms.controller.request.RegisterRequest;
import by.zemich.userms.controller.response.UserFullResponse;
import by.zemich.userms.dao.entity.User;
import by.zemich.userms.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {
        User registered = userService.register(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registered.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Page<UserFullResponse>> findAll(
            @RequestParam(name = "page_number", defaultValue = "0") int pageNumber,
            @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(name = "sort_by", defaultValue = "registerAt") String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        Page<UserFullResponse> response = userService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserFullResponse> deactivate(@PathVariable UUID id) {
        UserFullResponse response = userService.deactivate(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFullResponse> findById(@PathVariable UUID id) {
        UserFullResponse response = userService.findById(id);
        return ResponseEntity.ok(response);
    }
}
