package by.zemich.userms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "secret.sign")
@Data
public class SecureProperties {
    private String key;
}
