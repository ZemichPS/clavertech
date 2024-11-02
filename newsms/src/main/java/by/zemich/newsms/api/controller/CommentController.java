package by.zemich.newsms.api.controller;

import by.zemich.newsms.api.controller.dto.request.CommentRequest;
import by.zemich.newsms.api.controller.dto.response.CommentFullResponse;
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
    public ResponseEntity<URI> create(@RequestBody CommentRequest newCommentRequest) {
        Comment savedComment = commentRestService.save(newCommentRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedComment.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentFullResponse> getById(@PathVariable UUID id) {
        CommentFullResponse comment = commentRestService.findById(id);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentFullResponse> updateById(@PathVariable UUID id, @RequestBody CommentRequest newCommentRequest) {
        CommentFullResponse updatedComment = commentRestService.update(id, newCommentRequest);
        return ResponseEntity.ok(updatedComment);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentFullResponse> patchById(
            @PathVariable UUID id,
            @RequestBody CommentRequest commentRequest
    ) {
        CommentFullResponse updatedComment = commentRestService.partialUpdateUpdate(id, commentRequest);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        commentRestService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
