package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.persistence.GroupRepository;

import java.util.Date;
import java.util.List;

public interface GroupService {

    int addGroup(String name, Date creationDate, Date deadline, double groupGrade, Template template);
    void deleteGroup(int id);
    Group getById(int id);
    List<Group> getByTemplate(Template template);
}
