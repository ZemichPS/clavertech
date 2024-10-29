package by.zemich.newsms.core.service;

import by.zemich.newsms.api.dao.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentCrudService {
    private final CommentRepository commentRepository;

}
