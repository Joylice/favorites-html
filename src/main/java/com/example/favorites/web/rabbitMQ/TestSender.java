package com.example.favorites.web.rabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by cuiyy on 2017/11/16.
 */
@Component
public class TestSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context="test"+new Date();
        System.out.println("Sender:"+context);
        this.rabbitTemplate.convertAndSend("test",context);
    }
}
