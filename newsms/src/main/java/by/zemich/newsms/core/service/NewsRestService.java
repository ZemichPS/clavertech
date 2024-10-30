package by.zemich.newsms.core.service;

import by.zemich.newsms.api.controller.dto.request.NewsRequest;
import by.zemich.newsms.api.controller.dto.response.NewsResponse;
import by.zemich.newsms.api.dao.CommentRepository;
import by.zemich.newsms.api.dao.NewsRepository;
import by.zemich.newsms.core.domain.News;
import by.zemich.newsms.core.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsRestService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
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
}
