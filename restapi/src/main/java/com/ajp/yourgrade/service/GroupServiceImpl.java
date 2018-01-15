package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.persistence.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    @Override
    public Group getById(int id) {
        return groupRepository.findById(id);
    }

    @Override
    public List<Group> getByTemplate(Template template) {
        return  groupRepository.findByTemplate(template);
    }
}
