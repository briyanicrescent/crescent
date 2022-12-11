package com.hotel.crescent.cache;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

@Configuration
public class RedisConfiguration {
	
	@Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
            configurationMap.put("ID_REFRESH_TOKEN", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(4))); 
            configurationMap.put("ID_REFRESHED_ACCESS_TOKEN", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(10))); 
            configurationMap.put("ID_REFRESH_IN_PROGRESS", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(10)));     
            builder.withInitialCacheConfigurations(configurationMap);
        };
    }

}
