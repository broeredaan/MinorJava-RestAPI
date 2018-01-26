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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import org.junit.Test;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
 public class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupServiceImpl;

    @Mock
    private GroupServiceImpl groupServiceImplMock;

    @Mock
    private Template templateMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Date creationDate = new java.util.Date(2018, 01, 02, 12, 00, 00 );
    private Date deadline = new java.util.Date(2018, 01, 30, 12, 00, 00);
    private Group group = new Group("Test",creationDate,deadline,8,templateMock,true);

    //This method checks if the function addGroup is called the appropriate amount of times.
    @Test
    public void addGroup() {
        //Calls the method.
        groupServiceImplMock.addGroup("group", creationDate, deadline, 8.0, templateMock, true);
        //Checks how many times the method has been called.
        verify(groupServiceImplMock, only()).addGroup("group", creationDate, deadline, 8.0, templateMock, true);
    }

    //This method checks if the function deleteGroup is called the appropriate amount of times.
    @Test
    public void deleteGroup() {
        //Calls the method.
        groupServiceImplMock.deleteGroup( 0);
        //Checks how many times the method has been called.
        verify(groupServiceImplMock, only()).deleteGroup( 0);
    }

    //This method checks if the function setApproved is called the appropriate amount of times.
    @Test
    public void setApproved() {
        //Calls the method.
        groupServiceImplMock.setApproved(true, 0);
        //Checks how many times the method has been called.
        verify(groupServiceImplMock, only()).setApproved(true, 0);
    }

    //This method checks if the function setSend is called the appropriate amount of times.
    @Test
    public void setSend() {
        //Calls the method.
        groupServiceImplMock.setSend(true, 0);
        //Checks how many times the method has been called.
        verify(groupServiceImplMock, only()).setSend(true, 0);
    }

    //This method tests the return values of the method getById.
    @Test
    public void getById() {
        //Give value that will be returned when a Group is searched in the repo (id)
        when(groupRepository.findById(0)).thenReturn(group);
        //Get a group from the service
        Group result = groupServiceImpl.getById(0);
        //Check if the values are correct.
        assertEquals(0,result.getId());
        assertEquals("Test", result.getName());
        assertEquals(creationDate, result.getCreationDate());
        assertEquals(deadline, result.getDeadline());
        assertEquals(8, result.getGroupGrade());
    }

    //This method tests the return values of the method getByTemplate.
    @Test
    public void getByTemplate() {
        //Create a List so it can be returned by the Mock class.
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        //Give value that will be returned when a Group is searched in the repo (Template).
        when(groupRepository.findByTemplate(templateMock)).thenReturn(groupList);
        //Get a Group from the service then add it to the List.
        List<Group> result = groupServiceImpl.getByTemplate(templateMock);
        //Check if the values are correct.
        for (Group resultGroup : result) {
            assertEquals(0,resultGroup.getId());
            assertEquals("Test", resultGroup.getName());
            assertEquals(creationDate, resultGroup.getCreationDate());
            assertEquals(deadline, resultGroup.getDeadline());
            assertEquals(8, resultGroup.getGroupGrade());
        }
    }
}