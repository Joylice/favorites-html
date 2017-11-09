package com.example.favorites.web.controller;

import com.example.favorites.web.domain.User;
import com.example.favorites.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cuiyy on 2017/11/6.
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String getToken(@RequestBody User user) {
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
        return "";
    }


    @ResponseBody
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
