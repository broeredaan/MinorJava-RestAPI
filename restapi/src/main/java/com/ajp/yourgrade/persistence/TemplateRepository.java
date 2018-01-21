package com.ajp.yourgrade.persistence;

import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TemplateRepository extends CrudRepository<Template, Long> {

    Template findById(int id);
    List<Template> findByUser(User user);
    void deleteById(int id);
}
