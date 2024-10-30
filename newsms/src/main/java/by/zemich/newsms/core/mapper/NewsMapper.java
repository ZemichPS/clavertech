package by.zemich.newsms.core.mapper;

import by.zemich.newsms.api.controller.dto.request.NewsRequest;
import by.zemich.newsms.api.controller.dto.response.NewsResponse;
import by.zemich.newsms.core.domain.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    News mapToEntity(NewsRequest newsRequest);
    NewsResponse mapToDto(News news);
}
