package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.model.TemplateBody;
import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.service.TemplateService;
import com.ajp.yourgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "v1/template")
public class TemplateController {

    private UserService userService;
    private TemplateService templateService;

    public TemplateController(@Autowired UserService userService, TemplateService templateService) {
        this.userService = userService;
        this.templateService = templateService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<Set<Template>> getUserTemplates(@RequestParam(value = "userToken") String token) {
        if (!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(user.getTemplates());
    }

    @RequestMapping(method = RequestMethod.GET, path = "single")
    public ResponseEntity<Template> getTemplate(@RequestParam(value = "userToken") String token,
                                                @RequestParam(value = "id") int id) {
        if (!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Template template = templateService.getTemplateById(id);
        if(template.getUser().getId() != userService.getUserByToken(token).getId()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(template);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "create")
    public ResponseEntity<Boolean> createTemplate(@RequestBody TemplateBody body) {
        if (!userService.isLastUserToken(body.getUserToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(body.getUserToken());
        templateService.addTemplate(body.getName(), body.getGradeDeviation(), body.getIsCommentNeeded(), user);

        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "delete")
    public ResponseEntity<Boolean> deleteTemplate(@RequestParam(value = "userToken") String token,
                                                  @RequestParam(value = "templateId") int id) {
        if (!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Template template = templateService.getTemplateById(id);
        if(template.getUser().getId() != userService.getUserByToken(token).getId()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        templateService.deleteTemplate(id);

        return ResponseEntity.ok(true);
    }
}
