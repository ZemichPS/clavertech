package by.zemich.newsms.core.service;

import by.zemich.newsms.api.controller.dto.request.NewsPageRequest;
import by.zemich.newsms.api.controller.dto.request.NewsRequest;
import by.zemich.newsms.api.controller.dto.response.CommentFullResponse;
import by.zemich.newsms.api.controller.dto.response.NewsResponse;
import by.zemich.newsms.api.controller.dto.response.ShortCommentResponse;
import by.zemich.newsms.api.dao.CommentRepository;
import by.zemich.newsms.api.dao.NewsRepository;
import by.zemich.newsms.core.domain.Comment;
import by.zemich.newsms.core.domain.News;
import by.zemich.newsms.core.mapper.CommentMapper;
import by.zemich.newsms.core.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class NewsRestService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public News save(NewsRequest newsRequest) {
        News news = newsMapper.mapToEntity(newsRequest);
        return newsRepository.save(news);
    }

    public NewsResponse findById(UUID id) {

        return null;
    }

    public void deleteById(UUID id) {
        commentRepository.deleteById(id);
    }

    public NewsResponse patchById(UUID id, NewsRequest newsRequest) {
        return null;
    }

    public NewsResponse update(UUID id, NewsRequest newsRequest) {
        return null;
    }


    public PageImpl<ShortCommentResponse> getCommentsPage(NewsPageRequest pageRequest) {
        Sort sortBy = Sort.by(Sort.Direction.DESC, pageRequest.getSortBy());

        Pageable pageable = PageRequest.of(
                pageRequest.getPageNumber(),
                pageRequest.getPageSize(),
                sortBy
        );

        Page<Comment> allCommentsPage = commentRepository.findAllByNewsId(pageRequest.getId(), pageable);
        List<ShortCommentResponse> responses = allCommentsPage.get()
                .map(commentMapper::mapToDTO)
                .toList();
        return new PageImpl<>(responses, pageable, allCommentsPage.getTotalElements());
    }


    public CommentFullResponse getCommentByNewsIdAndCommentId(UUID newsId, UUID commentId) {
        return commentRepository.findByIdAndNewsId(commentId, newsId)
                .map(commentMapper::mapToFullResponse)
                .orElseThrow();
    }
}
