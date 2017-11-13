package com.example.favorites.web;

import com.example.favorites.web.GitHub.Item;
import com.example.favorites.web.GitHub.MyHandler;
import com.example.favorites.web.domain.User;
import com.example.favorites.web.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * Created by cuiyy on 2017/11/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class authServiceTest extends jdk.internal.org.xml.sax.helpers.DefaultHandler {
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

    @Test
    public void xmlTest() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        MyHandler myHandler = new MyHandler();
        CustomHandler customHandler = new CustomHandler();
        SAXParser saxParser = spf.newSAXParser();

        saxParser.parse("profile.xml", customHandler);
        List<UserItem> itemList = customHandler.getUserItemList();

        for (UserItem i : itemList) {
            System.out.println(i);
        }

        System.out.println("Over.....");
    }
}


