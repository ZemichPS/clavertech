package by.zemich.newsms.core.mapper;

import by.zemich.newsms.api.controller.dto.request.CommentRequest;
import by.zemich.newsms.core.domain.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment mapToEntity(CommentRequest newCommentRequest);
    Comment mapToEntity(Comment newComment, CommentRequest newCommentRequest);
    Comment mapToEntity(Comment newComment, Map<String, Object> updates);


}
