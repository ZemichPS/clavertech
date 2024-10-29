package by.zemich.newsms.api.controller.dto.request;

import by.zemich.newsms.core.domain.NewsId;
import lombok.Data;

@Data
public class CommentRequest {
    private String text;
    private String username;
    private NewsId newsId;
}
