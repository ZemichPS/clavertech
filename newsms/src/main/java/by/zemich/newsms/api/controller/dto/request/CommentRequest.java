package by.zemich.newsms.api.controller.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentRequest {
    private String text;
    private String username;
    private UUID newsId;
}
