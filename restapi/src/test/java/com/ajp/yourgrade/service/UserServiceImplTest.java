package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.persistence.UserRepository;
import com.ajp.yourgrade.persistence.UserTokenRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private UserTokenRepository userTokenRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void addUser() {

    }

    @Test
    public void deleteUser() {
        when(userRepository.findById(0)).thenReturn(new User("af", "adf.nl", true, "adf", "nl"));
            
    }

    @Test
    public void getUserById() {
        when(userRepository.findById(0)).thenReturn(new User("af", "adf.nl", true, "adf", "nl"));
        User result = userRepository.findById(0);
        assertEquals(0,result.getId());
        assertEquals("af", result.getName());
        assertEquals("adf.nl", result.getEmail());
        assertEquals(true, result.isAdmin());
        assertEquals("adf", result.getPassword());
        assertEquals("nl",result.getLanguage());
    }


}