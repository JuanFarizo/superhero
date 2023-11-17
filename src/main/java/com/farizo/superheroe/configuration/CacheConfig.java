package com.farizo.superheroe.configuration;

import com.farizo.superheroe.domain.Superhero;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.github.benmanes.caffeine.cache.Caffeine.newBuilder;
import static java.util.concurrent.TimeUnit.MINUTES;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(List.of(
                cache(Superhero.NAME, 720, 100)
        ));
        return manager;
    }

    CaffeineCache cache(String name, int minutes, long size) {
        return new CaffeineCache(
                name, newBuilder().expireAfterWrite(minutes, MINUTES).maximumSize(size).build());
    }
}
