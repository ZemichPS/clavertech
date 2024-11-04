package by.zemich.newsms.core.mapper;

import by.zemich.newsms.core.domain.News;
import by.zemich.newsms.core.domain.NewsDoc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NewsDocMapper {

    NewsDoc mapEntityToDoc(News news);

    void mapEntityToExistingDoc(News news, @MappingTarget NewsDoc newsDoc);

}
