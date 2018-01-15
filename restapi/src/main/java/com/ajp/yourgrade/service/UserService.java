package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;

import java.util.List;

public interface UserService {
    void createUser(String name, String email, boolean isAdmin, String password, String language);
    User getUserById(int id);
    User findByEmail(String email);
}
