package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.persistence.GroupRepository;

import java.util.List;

public interface GroupService {

    Group getById(int id);
    List<Group> getByTemplate(Template template);
}
