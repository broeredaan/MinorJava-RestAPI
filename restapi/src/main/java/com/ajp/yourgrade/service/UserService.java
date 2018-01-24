package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;

public interface UserService {
    void addUser(String name, String email, boolean isAdmin, String password, String language);
    User findByEmail(String email);
    String login(int id);
    User getUserByToken(String token);
    boolean isLastUserToken(String token);
}
