package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.only;

@RunWith(SpringJUnit4ClassRunner.class)
class TemplateServiceImplTest {

    private User u = new User("af", "adf.nl", true, "adf", "nl");


    @Mock
    private TemplateServiceImpl templateServiceImpl;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTemplate() {
        User userTest = mock(User.class, Mockito.RETURNS_DEEP_STUBS);
        templateServiceImpl.addTemplate("Name", 2, true, u);
        verify(templateServiceImpl, only()).addTemplate("Name", 2, true, u);
    }

    @Test
    void deleteTemplate() {

    }

    @Test
    void getTemplateById() {

    }

    @Test
    void getTemplateByUser() {

    }
}