package by.zemich.newsms.core.mapper;

import by.zemich.newsms.api.controller.dto.request.NewsRequest;
import by.zemich.newsms.api.controller.dto.response.NewsFullResponse;
import by.zemich.newsms.core.domain.Author;
import by.zemich.newsms.core.domain.News;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "author", qualifiedByName = "mapAuthor")
    News mapToEntity(NewsRequest newsRequest);

    void mapToExistingEntity(NewsRequest newsRequest, @MappingTarget News news);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    void partialMapToExistingEntity(NewsRequest newsRequest, @MappingTarget News news);

    NewsFullResponse mapToFullResponse(News news);

    @Named("mapAuthor")
    default Author mapAuthor(NewsRequest newsRequest) {
        Author author = new Author();
        author.setUsername(newsRequest.getAuthorUsername());
        author.setId(newsRequest.getAuthorId());
        return author;
    }

}
