package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.model.User;

import java.util.List;

public interface TemplateService {


    Template getTemplateById(int id);
    List<Template> getTemplateByUser(User user);
}
