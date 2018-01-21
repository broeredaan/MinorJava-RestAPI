package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.persistence.GroupRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
 public class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupServiceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addGroup() {
    }

    @Test
    public void deleteGroup() {
    }

    @Test
    public void getById() {
        Template templateMock = Mockito.mock(Template.class);
        //Give value that will be returned when a user is searched in the repo (id)
        when(groupRepository.findById(0)).thenReturn(new Group("Test",new java.util.Date(),new java.util.Date(),8,templateMock,true));
        //Get a user from the service
        Group result = groupServiceImpl.getById(0);
        //Check if the values are correct.
        assertEquals(0,result.getId());
        assertEquals("Test", result.getName());
        assertEquals(new java.util.Date(), result.getCreationDate());
        assertEquals(new java.util.Date(), result.getDeadline());
        assertEquals(8, result.getGroupGrade());
    }

    @Test
    public void getByTemplate() {
        Template templateMock = Mockito.mock(Template.class);
        Group group = new Group("Test",new java.util.Date(),new java.util.Date(),8,templateMock,true);
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        when(groupRepository.findByTemplate(templateMock)).thenReturn(groupList);
        List<Group> result = groupServiceImpl.getByTemplate(templateMock);
        for (Group resultGroup : result) {
            assertEquals(0,resultGroup.getId());
            assertEquals("Test", resultGroup.getName());
            assertEquals(new java.util.Date(), resultGroup.getCreationDate());
            assertEquals(new java.util.Date(), resultGroup.getDeadline());
            assertEquals(8, resultGroup.getGroupGrade());
        }
    }

    @Test
    public void setApproved() {
    }

    @Test
    public void setSend() {
    }
}