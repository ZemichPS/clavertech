package by.zemich.newsms.core.mapper;

import by.zemich.newsms.api.controller.dto.request.CommentRequest;
import by.zemich.newsms.api.controller.dto.response.CommentFullResponse;
import by.zemich.newsms.api.controller.dto.response.ShortCommentResponse;
import by.zemich.newsms.core.domain.Comment;
import org.mapstruct.*;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    //@Mapping(source = "text", target = "text")
    Comment mapToEntity(CommentRequest newCommentRequest);

    void mapToExistingEntity(CommentRequest newCommentRequest, @MappingTarget Comment existingComment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialMapToExistingEntity(CommentRequest newCommentRequest, @MappingTarget Comment existingComment);

    ShortCommentResponse mapToDTO(Comment comment);

    CommentFullResponse mapToFullResponse(Comment comment);


}
