package by.zemich.newsms.core.domain;

import by.zemich.newsms.api.dao.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class NewsCrudService {
    private final NewsRepository newsRepository;

    Optional<News> findById(UUID id) {
        return newsRepository.findById(id);
    }


}
