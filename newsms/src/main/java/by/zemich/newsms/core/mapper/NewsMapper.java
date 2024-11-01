package by.zemich.newsms.core.mapper;

import by.zemich.newsms.api.controller.dto.request.NewsRequest;
import by.zemich.newsms.api.controller.dto.response.NewsFullResponse;
import by.zemich.newsms.core.domain.News;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    News mapToEntity(NewsRequest newsRequest);

    @Mappings({
            @Mapping(target = "title", source = "title"),
    })
    void mapToExistingEntity(NewsRequest newsRequest, @MappingTarget News news);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialMapToExistingEntity(NewsRequest newsRequest, @MappingTarget News news);

    @Mappings({
            @Mapping(target = "title", source = "title"),
    })
    NewsFullResponse mapToFullResponse(News news);

}
