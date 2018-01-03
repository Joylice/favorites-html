package com.example.favorites.web.rabbitMQ;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by cuiyy on 2017/11/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMq {

    @Autowired
    private TestSender testSender;

    @Test
    public void test() {
        testSender.send();
    }

}
