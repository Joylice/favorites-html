package com.example.favorites.web.controller;

import com.example.favorites.web.domain.User;
import com.example.favorites.web.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cuiyy on 2017/11/6.
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController {
    private UserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository myUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = myUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String  getToken(@RequestBody User user) {
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
        return "";
    }
}
