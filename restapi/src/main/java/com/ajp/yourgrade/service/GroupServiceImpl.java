package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.persistence.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    @Override
    public void addGroup(String name, Date creationDate, Date deadline, double groupGrade, Template template) {
        groupRepository.save(new Group(name, creationDate,deadline,groupGrade,template));
    }

    @Override
    public void deleteGroup(int id) {
        groupRepository.delete(groupRepository.findById(id));
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