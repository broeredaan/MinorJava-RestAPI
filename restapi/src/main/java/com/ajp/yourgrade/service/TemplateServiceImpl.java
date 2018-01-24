package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.persistence.TemplateRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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

        templateRepository.deleteById(id);
    }

    @Override
    public Template getTemplateById(int id) {
       return templateRepository.findById(id);
    }
}
