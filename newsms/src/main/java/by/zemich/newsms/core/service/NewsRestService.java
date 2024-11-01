package by.zemich.newsms.core.service;

import by.zemich.newsms.api.controller.dto.request.NewsPageRequest;
import by.zemich.newsms.api.controller.dto.request.NewsRequest;
import by.zemich.newsms.api.controller.dto.response.CommentFullResponse;
import by.zemich.newsms.api.controller.dto.response.NewsFullResponse;
import by.zemich.newsms.api.controller.dto.response.ShortCommentResponse;
import by.zemich.newsms.core.domain.Comment;
import by.zemich.newsms.core.domain.News;
import by.zemich.newsms.core.mapper.CommentMapper;
import by.zemich.newsms.core.mapper.NewsMapper;
import by.zemich.newsms.core.service.api.CommentCrudService;
import by.zemich.newsms.core.service.api.NewsCrudService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class NewsRestService {
    private final NewsCrudService newsCrudService;
    private final CommentCrudService commentCrudService;
    private final CommentMapper commentMapper;
    private final NewsMapper newsMapper;

    public News save(NewsRequest newsRequest) {
        News news = newsMapper.mapToEntity(newsRequest);
        return newsCrudService.save(news);
    }

    public NewsFullResponse findById(UUID id) {
        return newsCrudService.findById(id)
                .map(newsMapper::mapToFullResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("News with id %s is nowhere to be found".formatted(id.toString()))
                );
    }

    public void deleteById(UUID id) {
        newsCrudService.deleteById(id);
    }

    public NewsFullResponse patchById(UUID id, NewsRequest newsRequest) {
        return newsCrudService.findById(id)
                .map(news -> {
                    newsMapper.partialMapToExistingEntity(newsRequest, news);
                    return news;
                })
                .map(newsCrudService::save)
                .map(newsMapper::mapToFullResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("News with id %s is nowhere to be found".formatted(id.toString()))
                );
    }

    public NewsFullResponse update(UUID id, NewsRequest newsRequest) {
        return newsCrudService.findById(id)
                .map(news -> {
                    newsMapper.mapToExistingEntity(newsRequest, news);
                    return news;
                })
                .map(newsCrudService::save)
                .map(newsMapper::mapToFullResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("News with id %s is nowhere to be found".formatted(id.toString()))
                );
    }


    public PageImpl<ShortCommentResponse> getCommentsPage(NewsPageRequest pageRequest) {
        Pageable pageable = formPageable(pageRequest);
        Page<Comment> allCommentsPage = commentCrudService.findAllByNewsId(pageRequest.getId(), pageable);
        List<ShortCommentResponse> responses = allCommentsPage.get()
                .map(commentMapper::mapToDTO)
                .toList();
        return new PageImpl<>(responses, pageable, allCommentsPage.getTotalElements());
    }

    public CommentFullResponse getCommentByNewsIdAndCommentId(UUID newsId, UUID commentId) {
        if (!newsCrudService.existsById(newsId))
            throw new EntityNotFoundException("News with id %s is nowhere to be found".formatted(newsId.toString()));
        return commentCrudService.findByIdAndNewsId(commentId, newsId)
                .map(commentMapper::mapToFullResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("Comment with id %s is nowhere to be found".formatted(commentId.toString()))
                );
    }

    public PageImpl<NewsFullResponse> getNews(NewsPageRequest pageRequest) {
        Pageable pageable = formPageable(pageRequest);
        Page<News> newsPage = newsCrudService.findAll(pageable);
        final List<NewsFullResponse> fullResponses = newsPage.getContent().stream()
                .map(newsMapper::mapToFullResponse)
                .toList();
        return new PageImpl<>(fullResponses, pageable, newsPage.getTotalElements());
    }

    private Pageable formPageable(NewsPageRequest pageRequest) {
        Sort sortBy = Sort.by(Sort.Direction.DESC, pageRequest.getSortBy());
        return PageRequest.of(
                pageRequest.getPageNumber(),
                pageRequest.getPageSize(),
                sortBy
        );
    }
}
