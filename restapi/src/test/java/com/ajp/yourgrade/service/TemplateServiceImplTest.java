package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.persistence.TemplateRepository;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class TemplateServiceImplTest {

    @Mock
    private TemplateRepository templateRepository;

    @InjectMocks
    private TemplateServiceImpl templateServiceImpl;

    @Mock
    private TemplateServiceImpl templateServiceImplMock;
    @Mock
    private User userMock;

    private Template template = new Template("Test",1,true,userMock);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTemplate() {
        templateServiceImplMock.addTemplate("Name", 2, true, userMock);
        verify(templateServiceImplMock, only()).addTemplate("Name", 2, true, userMock);
    }

    @Test
    public void deleteTemplate() {
        templateServiceImplMock.deleteTemplate( 2);
        verify(templateServiceImplMock, only()).deleteTemplate( 2);
    }

    @Test
    public void getTemplateById() {
        //Give value that will be returned when a user is searched in the repo (id)
        when(templateRepository.findById(0)).thenReturn(template);
        //Get a user from the service
        Template result = templateServiceImpl.getTemplateById(0);
        //Check if the values are correct.
        assertEquals(0,result.getId());
        assertEquals("Test", result.getName());
        assertEquals(1, result.getGradeDeviation());
        assertEquals(true, result.isCommentNeeded());

    }

    @Test
    public void getTemplateByUser() {
        List<Template> templateList = new ArrayList<Template>();
        templateList.add(template);
        when(templateRepository.findByUser(userMock)).thenReturn(templateList);
        List<Template> result = templateServiceImpl.getTemplateByUser(userMock);
        for (Template template : result) {
            assertEquals(0,template.getId());
            assertEquals("Test", template.getName());
            assertEquals(1, template.getGradeDeviation());
            assertEquals(true, template.isCommentNeeded());
        }
    }
}