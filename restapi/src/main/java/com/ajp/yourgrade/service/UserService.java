package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.model.UserToken;

import java.util.List;

public interface UserService {
    void addUser(String name, String email, boolean isAdmin, String password, String language);
    void deleteUser(int id);
    User getUserById(int id);
    User findByEmail(String email);
    String login(int id);
    User getUserByToken(String token);
    boolean isLastUserToken(String token);
}
