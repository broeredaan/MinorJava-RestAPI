package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.persistence.GroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    @Override
    public int addGroup(String name, Date creationDate, Date deadline, double groupGrade, Template template, boolean isSend) {
        Group group = groupRepository.save(new Group(name, creationDate,deadline,groupGrade,template, isSend));
        return group.getId();
    }

    @Override
    public void deleteGroup(int id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Group getById(int id) {
        return groupRepository.findById(id);
    }

    @Override
    public List<Group> getByTemplate(Template template) {
        return  groupRepository.findByTemplate(template);
    }

    @Override
    public void setApproved(boolean isApproved, int id) {
        Group group = getById(id);
        group.setApproved(isApproved);
        groupRepository.save(group);
    }

    @Override
    public void setSend(boolean isSend, int id) {
        Group group = getById(id);
        group.setSend(true);
        groupRepository.save(group);
    }
}
