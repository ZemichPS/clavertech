package by.zemich.newsms.api.controller.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NewsResponse {
    private UUID id;
    private LocalDateTime time;
    private String title;
    private String text;
}
