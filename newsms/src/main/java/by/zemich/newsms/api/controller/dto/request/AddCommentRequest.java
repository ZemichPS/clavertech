package by.zemich.newsms.api.controller.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AddCommentRequest {
    private UUID id;
    private LocalDateTime date;
    private String text;
    private UUID newsId;
}
