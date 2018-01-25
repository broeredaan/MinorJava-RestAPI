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

    @Test
    public void createMember() {
        groupMemberServiceImplMock.createMember("Test", "email", true, group);
        verify(groupMemberServiceImplMock, only()).createMember("Test", "email", true, group);
    }

    @Test
    public void deleteMember() {
        groupMemberServiceImplMock.deleteMember(0);
        verify(groupMemberServiceImplMock, only()).deleteMember(0);
    }

    @Test
    public void getMembersByGroup() {

        List<GroupMember> groupMemberList = new ArrayList<GroupMember>();
        groupMemberList.add(groupMember);
        when(groupMemberRepository.findByGroup(group)).thenReturn(groupMemberList);
        List<GroupMember> result = groupMemberServiceImpl.getMembersByGroup(group);
        for (GroupMember r : result) {
            assertEquals(0,r.getId());
            assertEquals("Test", r.getName());
            assertEquals("test", r.getEmail());
            assertEquals("Token", r.getToken());
            assertEquals(true, r.isHasSubmitted());
            assertEquals(group ,r.getGroup());
        }
}

    @Test
    public void getMemberById() {
        when(groupMemberRepository.findById(0)).thenReturn(groupMember);
        GroupMember result = groupMemberServiceImpl.getMemberById(0);
        assertEquals(0,result.getId());
        assertEquals("Test", result.getName());
        assertEquals("test", result.getEmail());
        assertEquals("Token", result.getToken());
        assertEquals(true, result.isHasSubmitted());
        assertEquals(group ,result.getGroup());
    }

    @Test
    public void getMemberByToken() {
        when(groupMemberRepository.findByToken("test")).thenReturn(groupMember);
        GroupMember result = groupMemberServiceImpl.getMemberByToken("test");
        assertEquals(0,result.getId());
        assertEquals("Test", result.getName());
        assertEquals("test", result.getEmail());
        assertEquals("Token", result.getToken());
        assertEquals(true, result.isHasSubmitted());
        assertEquals(group ,result.getGroup());
    }

    @Test
    public void saveMember(){
        groupMemberServiceImplMock.saveMember(groupMember);
        verify(groupMemberServiceImplMock, only()).saveMember(groupMember);
    }

    @Test
    public void getTokenByEmail(){
        when(groupMemberRepository.findByEmail("test")).thenReturn(groupMember);
        String result = groupMemberServiceImpl.getTokenByEmail("test");
        assertEquals("Token", result);
    }

    @Test
    public void getTokenById(){
        when(groupMemberRepository.findById(0)).thenReturn(groupMember);
        String result = groupMemberServiceImpl.getTokenById(0);
        assertEquals("Token", result);
    }

    @Test
    public void setFinalGrade() {
        groupMemberServiceImplMock.setFinalGrade(groupMember, 6.0);
        verify(groupMemberServiceImplMock, only()).setFinalGrade(groupMember, 6.0);
    }
}