package com.example.favorites.web;

import com.example.favorites.web.domain.User;
import com.example.favorites.web.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cuiyy on 2017/11/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class authServiceTest {
    @Autowired
    AuthService authService;

    @Test
    public void saveUser() {
        User user = new User();
        user.setId("0001");
        user.setUserName("wangm");
        user.setPassWord("wangm");
        user.setEmail("11@qq.com");

        authService.register(user);
    }
}
