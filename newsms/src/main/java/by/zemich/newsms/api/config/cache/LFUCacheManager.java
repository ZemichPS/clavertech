package by.zemich.newsms.api.config.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.List;

public class LFUCacheManager implements CacheManager {

    private final int maxSize;

    public LFUCacheManager(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public Cache getCache(String name) {
        return null;
    }

    @Override
    public Collection<String> getCacheNames() {
        return List.of();
    }
}
