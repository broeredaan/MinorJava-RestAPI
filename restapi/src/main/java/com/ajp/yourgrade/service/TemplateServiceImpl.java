package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.persistence.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService{

    private TemplateRepository templateRepository;

    public TemplateServiceImpl(TemplateRepository templateRepository){
        this.templateRepository = templateRepository;
    }

    @Override
    public void addTemplate(String name, double gradeDeviation, boolean isCommentNeeded, User user) {
        templateRepository.save(new Template(name,gradeDeviation,isCommentNeeded,user));
    }

    @Override
    public void deleteTemplate(int id) {
        templateRepository.delete(templateRepository.findById(id));
    }

    @Override
    public Template getTemplateById(int id) {
       return templateRepository.findById(id);
    }

    @Override
    public List<Template> getTemplateByUser(User user) {
        return templateRepository.findByUser(user);
    }
}
