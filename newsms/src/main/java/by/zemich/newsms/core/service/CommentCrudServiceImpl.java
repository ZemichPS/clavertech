package by.zemich.newsms.core.service;

import by.zemich.newsms.api.dao.CommentRepository;
import by.zemich.newsms.core.domain.Comment;
import by.zemich.newsms.core.service.api.CommentCrudService;
import by.zemich.newsms.core.service.api.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "comments")
@AllArgsConstructor
public class CommentCrudServiceImpl implements CommentCrudService {

    private final CommentRepository commentRepository;

    @Override
    @CachePut
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Cacheable(key = "#id")
    public Optional<Comment> findById(UUID id) {
        return commentRepository.findById(id);
    }

    @Override
    @CacheEvict
    public void deleteById(UUID id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Cacheable(key = "#pageable")
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }
}
