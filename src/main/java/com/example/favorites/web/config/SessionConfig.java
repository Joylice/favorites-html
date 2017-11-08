package com.example.favorites.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by cuiyy on 2017/10/26.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=86400*30)
public class SessionConfig {
}
