package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupMemberService {
    void createMember(String name, String email, boolean hasSubmitted, Group group);

    void deleteMember(int id);

    List<GroupMember> getMembersByGroup(int id);

    GroupMember getMemberById(int id);

    GroupMember getMemberByToken(String token);

    void saveMember(GroupMember member);

    String getTokenByEmail(String email);

    String getTokenById(int id);

    void setFinalGrade(GroupMember groupMember, double finalGrade);
}
