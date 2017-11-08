package com.example.favorites.web.service;


import com.example.favorites.web.domain.User;

public interface AuthService {
    User register(User userToAdd);

    String login(String username, String password);

    String refresh(String oldToken);
}
