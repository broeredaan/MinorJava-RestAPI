package com.ajp.yourgrade.service;


import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.persistence.GroupMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    private GroupMemberRepository groupMemberRepository;

    public GroupMemberServiceImpl(GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
    }


    @Override
    public void createMember() {

    }

    @Override
    public List<GroupMember> getMembersByGroup(Group group) {
        return groupMemberRepository.findByGroup(group);
    }

    @Override
    public GroupMember getMemberById(int id) {
        return groupMemberRepository.findById(id);
    }
}
