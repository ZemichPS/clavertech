package by.zemich.newsms.api.controller.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class NewsPageRequest {
    private UUID id;
    private int pageNumber;
    private int pageSize;
    private String sortBy;
}
