package by.zemich.newsms.api.controller.dto.request;

import by.zemich.newsms.core.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {
    private UUID authorId;
    private String title;
    private String text;
}
