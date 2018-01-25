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

    //This method checks if the function addTemplate is called the appropriate amount of times.
    @Test
    public void addTemplate() {
        //Calls the method.
        templateServiceImplMock.addTemplate("Name", 2, true, userMock);
        //Checks how many times the method has been called.
        verify(templateServiceImplMock, only()).addTemplate("Name", 2, true, userMock);
    }

    //This method checks if the function deleteTemplate is called the appropriate amount of times.
    @Test
    public void deleteTemplate() {
        //Calls the method.
        templateServiceImplMock.deleteTemplate( 2);
        //Checks how many times the method has been called.
        verify(templateServiceImplMock, only()).deleteTemplate( 2);
    }

    //This method tests the return values of the method getTemplateById.
    @Test
    public void getTemplateById() {
        //Give value that will be returned when a template is searched in the repo (id).
        when(templateRepository.findById(0)).thenReturn(template);
        //Get a template from the service.
        Template result = templateServiceImpl.getTemplateById(0);
        //Check if the values are correct.
        assertEquals(0,result.getId());
        assertEquals("Test", result.getName());
        assertEquals(1, result.getGradeDeviation());
        assertEquals(true, result.isCommentNeeded());

    }

    //This method tests the return values of the method getTemplateByUser.
    @Test
    public void getTemplateByUser() {
        //Create a List so it can be returned by the Mock class.
        List<Template> templateList = new ArrayList<Template>();
        templateList.add(template);
        //Give value that will be returned when a template is searched in the repo (user).
        when(templateRepository.findByUser(userMock)).thenReturn(templateList);
        //Get a template from the service then add it to the List.
        List<Template> result = templateServiceImpl.getTemplateByUser(userMock);
        //Check if the values are correct.
        for (Template template : result) {
            assertEquals(0,template.getId());
            assertEquals("Test", template.getName());
            assertEquals(1, template.getGradeDeviation());
            assertEquals(true, template.isCommentNeeded());
        }
    }
}