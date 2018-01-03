package com.example.favorites.web.rabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cuiyy on 2017/11/16.
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue Queue(){
        return new Queue("test");
    }
}
