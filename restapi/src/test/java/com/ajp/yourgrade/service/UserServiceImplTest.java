package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
<<<<<<< HEAD
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.only;

=======
import com.ajp.yourgrade.persistence.UserRepository;
import org.junit.Before;
>>>>>>> c8c896a41c71bc19c209ee006481dc1e85451991
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    private User u = new User("af", "adf.nl", true, "adf", "nl");

    @Mock
<<<<<<< HEAD
    private UserServiceImpl userServiceImpl;
=======
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
>>>>>>> c8c896a41c71bc19c209ee006481dc1e85451991

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
<<<<<<< HEAD
        userServiceImpl.deleteUser(0);
        verify(userServiceImpl, only()).deleteUser(0);
=======

>>>>>>> c8c896a41c71bc19c209ee006481dc1e85451991
    }

    @Test
    public void getUserById() {
<<<<<<< HEAD
        when(userServiceImpl.getUserById(0)).thenReturn(u);
        User result = userServiceImpl.getUserById(0);
=======
        //Give value that will be returned when a user is searched in the repo (id)
        when(userRepository.findById(0)).thenReturn(new User("af", "adf.nl", true, "adf", "nl"));
        //Get a user from the service
        User result = userService.getUserById(0);
        //Check if the values are correct.
>>>>>>> c8c896a41c71bc19c209ee006481dc1e85451991
        assertEquals(0,result.getId());
        assertEquals("af", result.getName());
        assertEquals("adf.nl", result.getEmail());
        assertEquals(true, result.isAdmin());
        assertEquals("adf", result.getPassword());
        assertEquals("nl",result.getLanguage());
    }


}