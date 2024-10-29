package by.zemich.newsms.core.service;

import by.zemich.newsms.api.controller.dto.request.CommentRequest;
import by.zemich.newsms.core.domain.Comment;
import by.zemich.newsms.core.mapper.CommentMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentRestService {

    private final CommentCrudService commentCrudService;

    public Comment save(CommentRequest commentRequest) {
        Comment newComment = CommentMapper.INSTANCE.map(commentRequest);
        return commentCrudService.save(newComment);
    }

    public Comment update(UUID id, CommentRequest commentRequest) {
        return commentCrudService.findById(id)
                .map(comment -> CommentMapper.INSTANCE.map(comment, commentRequest))
                .map(commentCrudService::save)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public Comment findById(UUID id) {
        return commentCrudService.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public void delete(UUID id) {
        commentCrudService.delete(id);
    }

    public Comment partialUpdateUpdate(UUID id, Map<String, Object> updates) {
        return commentCrudService.findById(id)
                .map(comment -> CommentMapper.INSTANCE.map(comment, updates))
                .map(commentCrudService::save)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }
}
