package by.zemich.newsms.core.service;

import by.zemich.newsms.api.controller.dto.request.AddNewsRequest;
import by.zemich.newsms.core.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentRestService {

    private final CommentCrudService commentCrudService;

    public Comment save(AddNewsRequest addNewsRequest) {
        Comment newComment = new Comment();
    }

}
