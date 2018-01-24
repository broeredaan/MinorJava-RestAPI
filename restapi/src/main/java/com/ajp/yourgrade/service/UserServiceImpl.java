package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.model.UserToken;
import com.ajp.yourgrade.persistence.UserRepository;
import com.ajp.yourgrade.persistence.UserTokenRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public boolean isLastUserToken(String token) {
        UserToken userToken = userTokenRepository.findByToken(token);
        User user = userToken.getUser();

        Comparator<UserToken> cmp = new Comparator<UserToken>() {
            @Override
            public int compare(UserToken o1, UserToken o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        };

        UserToken lastToken = Collections.max(user.getTokens(), cmp);

        return lastToken.getToken().equals(token);
    }
}

