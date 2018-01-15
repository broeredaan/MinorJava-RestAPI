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
    public void createMember(String name, String email, String token, boolean hasSubmitted, Group group) {
        groupMemberRepository.save(new GroupMember(name, email, token, hasSubmitted, group));
    }

    @Override
    public void deleteMember(int id) {
        groupMemberRepository.delete(groupMemberRepository.findById(id));

    }

    @Override
    public List<GroupMember> getMembersByGroup(Group group) {
        return groupMemberRepository.findByGroup(group);
    }

    @Override
    public GroupMember getMemberById(int id) {
        return groupMemberRepository.findById(id);
    }

    @Override
    public GroupMember getMemberByToken(String token) {
        return groupMemberRepository.findByToken(token);
    }
}
