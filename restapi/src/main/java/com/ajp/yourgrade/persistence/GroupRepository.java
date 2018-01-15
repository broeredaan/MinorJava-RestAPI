package com.ajp.yourgrade.persistence;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.Template;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {

    Group findById(int id);
    List<Group> findByTemplate(Template template);
}
