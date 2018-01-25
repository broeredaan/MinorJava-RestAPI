package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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


    //This method checks if the function addUser is called the appropriate amount of times.
    @Test
    public void addUser() {
        //Calls the method.
        userServiceImpl.addUser("af", "adf.nl", true, "adf", "nl");
        //Checks how many times the method has been called.
        verify(userServiceImpl, only()).addUser("af", "adf.nl", true, "adf", "nl");
    }

    //This method checks if the function deleteUser is called the appropriate amount of times.
    @Test
    public void deleteUser() {
        //Calls the method.
        userServiceImpl.deleteUser(0);
        //Checks how many times the method has been called.
        verify(userServiceImpl, only()).deleteUser(0);
    }

    //This method tests the return values of the method getUserById.
    @Test
    public void getUserById() {
        //Give value that will be returned when a user is searched in the repo (id).
        when(userServiceImpl.getUserById(0)).thenReturn(u);
        //Get a user from the service.
        User result = userServiceImpl.getUserById(0);
        //Check if the values are correct.
        assertEquals(0,result.getId());
        assertEquals("af", result.getName());
        assertEquals("adf.nl", result.getEmail());
        assertEquals(true, result.isAdmin());
        assertEquals("adf", result.getPassword());
        assertEquals("nl",result.getLanguage());
    }


}