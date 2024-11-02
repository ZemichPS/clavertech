package by.zemich.newsms.api.controller.dto.request;

import lombok.Data;

import java.util.UUID;


@Data
public class CommentRequest {
    private UUID newsId;
    private String username;
    private String text;
}
