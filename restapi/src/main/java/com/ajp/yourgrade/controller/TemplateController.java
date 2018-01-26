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

/**
 * The TemplateController is handles all the API calls for the template management
 */
@RestController
@RequestMapping(path = "v1/template")
public class TemplateController {

    //Internal reference to the services used by this class
    private UserService userService;
    private TemplateService templateService;

    /**
     * The constructor of the TemplateController, used for constructor injection
     * @param userService Save the reference to the passed-in userService inside this class
     * @param templateService Save the reference to the passed-in templateService inside this class
     */
    public TemplateController(@Autowired UserService userService, TemplateService templateService) {
        //Save the passed in references
        this.userService = userService;
        this.templateService = templateService;
    }

    /**
     * API call to get all the available templates
     * @param token RequestParam "userToken" retrieved after first login
     * @return Template A list of all templates
     */
    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<Set<Template>> getUserTemplates(@RequestParam(value = "userToken") String token) {
        if (!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(user.getTemplates());
    }

    /**
     * API call similar to v1/template, but used for a specific template
     * @param token RequestParam "userToken" retrieved after first login
     * @param id RequestParam "id" the id of the template, that is to be retrieved
     * @return Template One single template
     */
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

    /**
     * API call to create a new template
     * @param body Raw Json string filled with the template information, bases on the TemplateBody model
     * @return Boolean Returns true if the call was handled successfully
     */
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
