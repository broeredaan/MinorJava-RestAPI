package com.ajp.yourgrade.service;


import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.persistence.GroupMemberRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public  class GroupMemberServiceImpl implements GroupMemberService {

    private GroupMemberRepository groupMemberRepository;

    public GroupMemberServiceImpl(GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
    }

    @Override
    public void createMember(String name, String email, boolean hasSubmitted, Group group) {

        groupMemberRepository.save(new GroupMember(name, email, generateUuid(), hasSubmitted, group));
    }

    @Override
    public void deleteMember(int id) {
        groupMemberRepository.delete(groupMemberRepository.findById(id));

    }

    @Override
    public List<GroupMember> getMembersByGroup(int id) {
        return groupMemberRepository.findByGroup(new Group("", new Date(), new Date(), 7, null));
    }

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

    @Override
    public void saveMember(GroupMember member) {
        groupMemberRepository.save(member);
    }

    @Override
    public String getTokenByEmail(String email) {return groupMemberRepository.findByEmail(email).getToken();}

    @Override
    public void setFinalGrade(GroupMember groupMember, double finalGrade) {
        groupMember.setFinalGrade(finalGrade);
        groupMemberRepository.save(groupMember);
    }

    private String generateUuid() {
        UUID uuid = UUID.randomUUID();
        String tokenString = uuid.toString();
        return tokenString;
    }
}
