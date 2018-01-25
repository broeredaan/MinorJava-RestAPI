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

    @Test
    public void sendRequest() {
        mailServiceImpl.sendRequest("email", "name", "token", "href", "me");
        verify(mailServiceImpl, only()).sendRequest("email", "name", "token", "href", "me");
    }

    @Test
    public void sendFinished() {
        mailServiceImpl.sendFinished("email", "name", "groupname");
        verify(mailServiceImpl, only()).sendFinished("email", "name", "groupname");
    }
}