package com.example.favorites.web.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by cuiyy on 2017/11/16.
 */
@Component
@RabbitListener(queues = "test")
public class TestReceiver {

    @RabbitHandler
    public void process(String test) {
        System.out.println("Receiver:" + test);
    }
}
