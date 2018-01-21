package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void addUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserById() {
        /*User user = new User("TestDummy1", "TestDummy1@gmail.com", true, "Test","English");
        when(userService.getUserById(1)).thenReturn(user);*/

        System.out.println(userService.getUserById(1));
    }

    @Test
    void findByEmail() {
    }
}