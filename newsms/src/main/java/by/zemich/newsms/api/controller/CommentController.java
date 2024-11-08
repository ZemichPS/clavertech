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
                .path("/{commentId}")
                .buildAndExpand(savedComment.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentFullResponse> getById(@PathVariable UUID commentId) {
        CommentFullResponse comment = commentRestService.findById(commentId);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentFullResponse> updateById(@PathVariable UUID commentId, @RequestBody CommentRequest newCommentRequest) {
        CommentFullResponse updatedComment = commentRestService.update(commentId, newCommentRequest);
        return ResponseEntity.ok(updatedComment);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentFullResponse> patchById(
            @PathVariable UUID commentId,
            @RequestBody CommentRequest commentRequest
    ) {
        CommentFullResponse updatedComment = commentRestService.partialUpdateUpdate(commentId, commentRequest);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID commentId) {
        commentRestService.delete(commentId);
        return ResponseEntity.noContent().build();
    }


}
