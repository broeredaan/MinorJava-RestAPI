package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupMemberService {
    void createMember(String name, String email, String token, boolean hasSubmitted, Group group);

    void deleteMember(int id);

    List<GroupMember> getMembersByGroup(Group group);

    GroupMember getMemberById(int id);

    GroupMember getMemberByToken(String token);
}
