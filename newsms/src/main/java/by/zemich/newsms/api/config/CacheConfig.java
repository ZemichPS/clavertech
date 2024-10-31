package by.zemich.newsms.api.config;

import by.zemich.newsms.api.config.cache.LFUCacheManager;
import by.zemich.newsms.api.config.cache.LRUCacheManager;
import by.zemich.newsms.api.config.properties.CacheProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfig {

    @Bean
    @ConditionalOnProperty(
            name = "cache.algorithm",
            value = "LRU"
    )
    public CacheManager lruCacheManager(CacheProperties properties) {
        return new LRUCacheManager(properties.getSize());
    }


    @Bean
    @ConditionalOnProperty(
            name = "cache.algorithm",
            value = "LFU"
    )
    public CacheManager lfuCacheManager(CacheProperties cacheProperties) {
        return new LFUCacheManager(cacheProperties.getSize());
    }

}
