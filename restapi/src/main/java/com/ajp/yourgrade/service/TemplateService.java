package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.model.User;

import java.util.List;

public interface TemplateService {

    void addTemplate(String name, double gradeDeviation, boolean isCommentNeeded, User user);
    void deleteTemplate(int id);
    Template getTemplateById(int id);
}
