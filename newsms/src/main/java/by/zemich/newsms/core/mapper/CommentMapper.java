package by.zemich.newsms.core.mapper;

import by.zemich.newsms.api.controller.dto.request.CommentRequest;
import by.zemich.newsms.core.domain.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment map(CommentRequest newCommentRequest);
    Comment map(Comment newComment, CommentRequest newCommentRequest);
    Comment map(Comment newComment, Map<String, Object> updates);


}
