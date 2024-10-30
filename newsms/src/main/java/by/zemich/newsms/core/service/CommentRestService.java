package by.zemich.newsms.core.service;

import by.zemich.newsms.api.controller.dto.request.CommentRequest;
import by.zemich.newsms.api.dao.CommentRepository;
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

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public Comment save(CommentRequest commentRequest) {
        Comment newComment = commentMapper.mapToEntity(commentRequest);
        return commentRepository.save(newComment);
    }

    public Comment update(UUID id, CommentRequest commentRequest) {
        return commentRepository.findById(id)
                .map(comment -> commentMapper.mapToEntity(comment, commentRequest))
                .map(commentRepository::save)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public Comment findById(UUID id) {
        return commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public void delete(UUID id) {
        commentRepository.deleteById(id);
    }

    public Comment partialUpdateUpdate(UUID id, Map<String, Object> updates) {
        return commentRepository.findById(id)
                .map(comment -> commentMapper.mapToEntity(comment, updates))
                .map(commentRepository::save)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }
}
