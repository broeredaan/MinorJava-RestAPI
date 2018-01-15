package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import java.util.List;

public interface GroupMemberService {
    void createMember();
    List<GroupMember> getMembersByGroup(Group group);
    GroupMember getMemberById(int id);
}
