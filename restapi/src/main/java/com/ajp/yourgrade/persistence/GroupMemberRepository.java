package com.ajp.yourgrade.persistence;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupMemberRepository extends CrudRepository<GroupMember, Long> {

    GroupMember findById(int id);
    GroupMember findByEmail(String email);
    GroupMember findByToken(String token);
    List<GroupMember> findByGroup(Group group);
    void deleteById(int id);
}
