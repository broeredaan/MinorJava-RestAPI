package com.ajp.yourgrade.service;

import com.ajp.yourgrade.persistence.GroupMemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class GroupMemberServiceImplTest {
    @Mock
    GroupMemberRepository groupMemberRepository;

    @InjectMocks
    private GroupMemberServiceImpl groupMemberServiceImpl;

    @Test
    void createMember() {
    }

    @Test
    void deleteMember() {
    }

    @Test
    void testGetMembersByGroup() {


    }

    @Test
    void getMemberById() {
    }

    @Test
    void getMemberByToken() {
    }
}