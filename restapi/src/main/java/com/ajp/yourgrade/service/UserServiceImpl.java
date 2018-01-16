package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.model.UserToken;
import com.ajp.yourgrade.persistence.UserRepository;
import com.ajp.yourgrade.persistence.UserTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserTokenRepository userTokenRepository;

    public UserServiceImpl(UserRepository userRepository, UserTokenRepository userTokenRepository) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
    }

    @Override
    public void addUser(String name, String email, boolean isAdmin, String password, String language) {
        userRepository.save(new User(name, email, isAdmin, password, language));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(userRepository.findById(id));
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String login(int id) {
        User user = userRepository.findById(id);
        UserToken token = new UserToken(UUID.randomUUID().toString(), user, new Date());
        userTokenRepository.save(token);
        return token.getToken();
    }

    @Override
    public User getUserByToken(String token) {
        UserToken userToken = userTokenRepository.findByToken(token);
        return userToken.getUser();
    }
}

