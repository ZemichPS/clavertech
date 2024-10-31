package by.zemich.newsms.core.service;

import by.zemich.newsms.api.controller.dto.request.NewsPageRequest;
import by.zemich.newsms.api.controller.dto.response.CommentFullResponse;
import by.zemich.newsms.api.controller.dto.response.ShortCommentResponse;
import by.zemich.newsms.core.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsRestServiceFacade {
    private final NewsRestService newsRestService;
    private final CommentRestService commentRestService;






}
