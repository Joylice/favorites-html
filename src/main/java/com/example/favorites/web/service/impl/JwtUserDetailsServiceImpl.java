package com.example.favorites.web.service.impl;

import com.example.favorites.web.config.CustomHandler;
import com.example.favorites.web.domain.User;
import com.example.favorites.web.filter.JwtUserFactory;
import com.example.favorites.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * Created by cuiyy on 2017/11/8.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    /**
     * 用户权限验证的自定义方法 请求数据库或者读取XML文件的形式，
     * 校验用户合法性，并获取相应的token值；
     * **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            CustomHandler customHandler = new CustomHandler();
            saxParser.parse("profile.xml", customHandler);
            List<User> userList = customHandler.getUserList();

            for (User o : userList) {
                if (username.trim().equals(o.getUserName().trim())) {
                    user = o;
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // User user = userRepository.findByUserName(username);
            if (user == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            } else {
                return JwtUserFactory.create(user);
            }
        }
    }
}
