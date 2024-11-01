package by.zemich.newsms.config;

import by.zemich.newsms.config.cache.LFUCacheManager;
import by.zemich.newsms.config.cache.LRUCacheManager;
import by.zemich.newsms.config.properties.CacheProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@EnableCaching
@Configuration
@RequiredArgsConstructor

@Profile(value = "test")
public class CacheConfig {

    @Bean
    @ConditionalOnProperty(
            name = "cache.algorithm",
            havingValue = "LRU"
    )
    public CacheManager lruCacheManager(CacheProperties properties) {
        return new LRUCacheManager(properties.getSize());
    }


    @Bean
    @ConditionalOnProperty(
            name = "cache.algorithm",
            havingValue = "LFU"
    )
    public CacheManager lfuCacheManager(CacheProperties cacheProperties) {
        return new LFUCacheManager(cacheProperties.getSize());
    }

}