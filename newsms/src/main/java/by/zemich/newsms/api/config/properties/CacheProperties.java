package by.zemich.newsms.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cache")
@Data
public class CacheProperties {
    private int size;
    private String algorithm;
}
