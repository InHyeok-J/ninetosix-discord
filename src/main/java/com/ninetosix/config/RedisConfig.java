package com.ninetosix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = {"com.ninetosix"})
public class RedisConfig {
}
