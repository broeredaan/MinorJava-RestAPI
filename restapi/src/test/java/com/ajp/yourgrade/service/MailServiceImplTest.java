package com.ajp.yourgrade.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.only;

@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceImplTest {

    @Mock
    private MailServiceImpl mailServiceImpl;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    //This method checks if the function sendRequest is called the appropriate amount of times.
    @Test
    public void sendRequest() {
        //Calls the method.
        mailServiceImpl.sendRequest("email", "name", "token", "href", "me");
        //Checks how many times the method has been called.
        verify(mailServiceImpl, only()).sendRequest("email", "name", "token", "href", "me");
    }

    //This method checks if the function sendFinished is called the appropriate amount of times.
    @Test
    public void sendFinished() {
        //Calls the method.
        mailServiceImpl.sendFinished("email", "name", "groupname");
        //Checks how many times the method has been called.
        verify(mailServiceImpl, only()).sendFinished("email", "name", "groupname");
    }
}