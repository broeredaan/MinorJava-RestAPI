package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(String name, String email, boolean isAdmin, String password, String language) {
        userRepository.save(new User(name,email,isAdmin,password,language));
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

