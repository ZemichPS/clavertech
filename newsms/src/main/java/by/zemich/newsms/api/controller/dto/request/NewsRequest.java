package by.zemich.newsms.api.controller.dto.request;

import by.zemich.newsms.core.domain.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NewsRequest {
    private String title;
    private String text;
    private List<Comment> comments;
}
