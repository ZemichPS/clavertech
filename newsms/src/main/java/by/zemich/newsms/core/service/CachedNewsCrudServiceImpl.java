package by.zemich.newsms.core.service;

import by.zemich.newsms.api.dao.NewsRepository;
import by.zemich.newsms.core.domain.News;
import by.zemich.newsms.core.service.api.NewsCrudService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "news")
@AllArgsConstructor
public class CachedNewsCrudServiceImpl implements NewsCrudService {

    private final NewsRepository newsRepository;

    @Override
    @CachePut
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    @Cacheable(key = "#id")
    @Transactional(readOnly = true)
    public Optional<News> findById(UUID id) {
        return newsRepository.findById(id);
    }

    @Override
    @CacheEvict
    public void deleteById(UUID id) {
        newsRepository.deleteById(id);
    }

    @Override
    @Cacheable(key = "#pageable")
    @Transactional(readOnly = true)
    public Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    public boolean existsById(UUID id) {
        return newsRepository.existsById(id);
    }
}
