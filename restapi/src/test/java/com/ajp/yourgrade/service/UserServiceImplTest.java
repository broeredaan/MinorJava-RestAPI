package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    private User u = new User("af", "adf.nl", true, "adf", "nl");

    @Mock
    private UserServiceImpl userServiceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void addUser() {
        userServiceImpl.addUser("af", "adf.nl", true, "adf", "nl");
        verify(userServiceImpl, only()).addUser("af", "adf.nl", true, "adf", "nl");
    }

    @Test
    public void deleteUser() {
        userServiceImpl.deleteUser(0);
        verify(userServiceImpl, only()).deleteUser(0);
    }

    @Test
    public void getUserById() {
        when(userServiceImpl.getUserById(0)).thenReturn(u);
        User result = userServiceImpl.getUserById(0);
        assertEquals(0,result.getId());
        assertEquals("af", result.getName());
        assertEquals("adf.nl", result.getEmail());
        assertEquals(true, result.isAdmin());
        assertEquals("adf", result.getPassword());
        assertEquals("nl",result.getLanguage());
    }


}