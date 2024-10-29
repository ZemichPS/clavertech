package by.zemich.userms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "secret.sign")
@Data
public class JWTProperty {
    private String key;
    private String issuer;
    private int duration;
}
