package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.GroupMember;
import java.util.List;

public interface GroupMemberService {
    void createMember();
    List<GroupMember> getMembersByGroup(int id);
    GroupMember getMemberById(int id);
}
