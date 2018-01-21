package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.persistence.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void addUser() {

    }

    @Test
    public void deleteUser() {

    }

    @Test
    public void getUserById() {
        //Give value that will be returned when a user is searched in the repo (id)
        when(userRepository.findById(0)).thenReturn(new User("af", "adf.nl", true, "adf", "nl"));
        //Get a user from the service
        User result = userService.getUserById(0);
        //Check if the values are correct.
        assertEquals(0,result.getId());
        assertEquals("af", result.getName());
        assertEquals("adf.nl", result.getEmail());
        assertEquals(true, result.isAdmin());
        assertEquals("adf", result.getPassword());
        assertEquals("nl",result.getLanguage());
    }


}