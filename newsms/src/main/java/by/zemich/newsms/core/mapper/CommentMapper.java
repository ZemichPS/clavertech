package by.zemich.newsms.core.mapper;

import by.zemich.newsms.api.controller.dto.request.AddNewsRequest;
import by.zemich.newsms.core.domain.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment map(AddNewsRequest addNewsRequest);
}
