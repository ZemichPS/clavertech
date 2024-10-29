package by.zemich.newsms.core.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data
public class Author {
    private UUID id;
}
