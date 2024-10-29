package by.zemich.newsms.api.controller;

import by.zemich.newsms.api.controller.dto.request.CommentRequest;
import by.zemich.newsms.core.domain.Comment;
import by.zemich.newsms.core.service.CommentRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRestService commentRestService;

    @PostMapping
    public ResponseEntity<URI> add(@RequestBody CommentRequest newCommentRequest) {
        Comment savedComment = commentRestService.save(newCommentRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedComment.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> get(@PathVariable UUID id) {
        Comment comment = commentRestService.findById(id);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable UUID id, @RequestBody CommentRequest newCommentRequest) {
        Comment updatedComment = commentRestService.update(id, newCommentRequest);
        return ResponseEntity.ok(updatedComment);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comment> partialUpdate(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        Comment updatedComment = commentRestService.partialUpdateUpdate(id, updates);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        commentRestService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
