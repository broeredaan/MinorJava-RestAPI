package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.persistence.GroupMemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class GroupMemberServiceImplTest {

    @Mock
    private GroupMemberRepository groupMemberRepository;

    @Mock
     Group group;

    @Mock
    private Template template;
    @Mock
    private User user;

    @InjectMocks
    private GroupMemberServiceImpl groupMemberServiceImpl;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        group = Mockito.mock(Group.class);
    }

    @Test
    public void createMember() {
    }

    @Test
    public void deleteMember() {
    }

    @Test
    public void testGetMembersByGroup() {
//        List<GroupMember> userList = new ArrayList<>();
//        GroupMember groupMember = new GroupMember("John","johndoe@gmail.com","adf",true,group);
//        GroupMember groupMember2 = new GroupMember("John","johndoe@hotmail.com","adf",true,group);
//        userList.add(groupMember);
//        userList.add(groupMember2);
//        when(groupMemberRepository.findByGroup(group)).thenReturn(userList);
    }

    @Test
    public void getMemberById()  throws Exception{

        GroupMember groupMember = new GroupMember("John","johndoe@gmail.com","adf",true,group);
        when(groupMemberRepository.findById(0)).thenReturn(new GroupMember("John","johndoe@gmail.com","adf",true,group));
    }

    @Test
    public void getMemberByToken() {
    }
}