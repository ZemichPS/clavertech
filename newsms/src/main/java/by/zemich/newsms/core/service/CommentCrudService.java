package by.zemich.newsms.core.service;

import by.zemich.newsms.api.dao.CommentRepository;
import by.zemich.newsms.core.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentCrudService {
    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> findById(UUID id) {
        return commentRepository.findById(id);
    }

    public void delete(UUID id)  {
        commentRepository.deleteById(id);
    }

}
