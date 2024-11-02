package by.zemich.newsms.api.dao;

import by.zemich.newsms.core.domain.News;
import by.zemich.newsms.core.domain.NewsDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface NewsElasticSearchRepository extends ElasticsearchRepository<NewsDoc, UUID> {
}
