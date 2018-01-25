package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.persistence.GroupMemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.only;

@RunWith(SpringJUnit4ClassRunner.class)
public class GroupMemberServiceImplTest {

    @Mock
    private GroupMemberRepository groupMemberRepository;

    @Mock
    private Group group;

    @InjectMocks
    private GroupMemberServiceImpl groupMemberServiceImpl;

    @Mock
    private GroupMemberServiceImpl groupMemberServiceImplMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        group = Mockito.mock(Group.class);
    }

    private GroupMember groupMember = new GroupMember("Test", "test", "Token", true, group);

    //This method checks if the function createMember is called the appropriate amount of times.
    @Test
    public void createMember() {
        //Calls the method.
        groupMemberServiceImplMock.createMember("Test", "email", true, group);
        //Checks how many times the method has been called.
        verify(groupMemberServiceImplMock, only()).createMember("Test", "email", true, group);
    }

    //This method checks if the function deleteMember is called the appropriate amount of times.
    @Test
    public void deleteMember() {
        //Calls the method.
        groupMemberServiceImplMock.deleteMember(0);
        //Checks how many times the method has been called.
        verify(groupMemberServiceImplMock, only()).deleteMember(0);
    }

    //This method checks if the function saveMember is called the appropriate amount of times.
    @Test
    public void saveMember(){
        //Calls the method.
        groupMemberServiceImplMock.saveMember(groupMember);
        //Checks how many times the method has been called.
        verify(groupMemberServiceImplMock, only()).saveMember(groupMember);
    }

    //This method checks if the function setFinalGrade is called the appropriate amount of times.
    @Test
    public void setFinalGrade() {
        //Calls the method.
        groupMemberServiceImplMock.setFinalGrade(groupMember, 6.0);
        //Checks how many times the method has been called.
        verify(groupMemberServiceImplMock, only()).setFinalGrade(groupMember, 6.0);
    }

    //This method tests the return values of the method getTokenByEmail.
    @Test
    public void getTokenByEmail(){
        //Give value that will be returned when a token is searched in the repo (email)
        when(groupMemberRepository.findByEmail("test")).thenReturn(groupMember);
        //Get a token from the service
        String result = groupMemberServiceImpl.getTokenByEmail("test");
        //Check if the values are correct.
        assertEquals("Token", result);
    }

    //This method tests the return values of the method getTokenById.
    @Test
    public void getTokenById(){
        //Give value that will be returned when a token is searched in the repo (id)
        when(groupMemberRepository.findById(0)).thenReturn(groupMember);
        //Get a token from the service
        String result = groupMemberServiceImpl.getTokenById(0);
        //Check if the values are correct.
        assertEquals("Token", result);
    }

    //This method tests the return values of the method getMembersByGroup.
    @Test
    public void getMembersByGroup() {
        //Create a List so it can be returned by the Mock class.
        List<GroupMember> groupMemberList = new ArrayList<GroupMember>();
        groupMemberList.add(groupMember);
        //Give value that will be returned when a GroupMember is searched in the repo (Group)
        when(groupMemberRepository.findByGroup(group)).thenReturn(groupMemberList);
        //Get a GroupMember from the service then add it to the List.
        List<GroupMember> result = groupMemberServiceImpl.getMembersByGroup(group);
        //Check if the values are correct.
        for (GroupMember r : result) {
            assertEquals(0,r.getId());
            assertEquals("Test", r.getName());
            assertEquals("test", r.getEmail());
            assertEquals("Token", r.getToken());
            assertEquals(true, r.isHasSubmitted());
            assertEquals(null ,r.getGroup());
        }
}

    //This method tests the return values of the method getMemberById.
    @Test
    public void getMemberById() {
        //Give value that will be returned when a GroupMember is searched in the repo (id)
        when(groupMemberRepository.findById(0)).thenReturn(groupMember);
        //Get a GroupMember from the service
        GroupMember result = groupMemberServiceImpl.getMemberById(0);
        //Check if the values are correct.
        assertEquals(0,result.getId());
        assertEquals("Test", result.getName());
        assertEquals("test", result.getEmail());
        assertEquals("Token", result.getToken());
        assertEquals(true, result.isHasSubmitted());
        assertEquals(null ,result.getGroup());
    }

    //This method tests the return values of the method getMemberByToken.
    @Test
    public void getMemberByToken() {
        //Give value that will be returned when a GroupMember is searched in the repo (token)
        when(groupMemberRepository.findByToken("test")).thenReturn(groupMember);
        //Get a GroupMember from the service
        GroupMember result = groupMemberServiceImpl.getMemberByToken("test");
        //Check if the values are correct.
        assertEquals(0,result.getId());
        assertEquals("Test", result.getName());
        assertEquals("test", result.getEmail());
        assertEquals("Token", result.getToken());
        assertEquals(true, result.isHasSubmitted());
        assertEquals(null ,result.getGroup());
    }
}