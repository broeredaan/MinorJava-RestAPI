package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.model.*;
import com.ajp.yourgrade.persistence.*;
import com.ajp.yourgrade.service.GroupMemberService;
import com.ajp.yourgrade.service.GroupService;
import com.ajp.yourgrade.service.TemplateService;
import com.ajp.yourgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("v1")
public class RestController {

    private UserService userService;
    private TemplateService templateService;
    private GroupService groupService;
    private GroupMemberService groupMemberService;

    /**
     *
     */
    public RestController(@Autowired UserService userService, TemplateService templateService, GroupService groupService, GroupMemberService groupMemberService) {
        this.userService = userService;
        this.templateService = templateService;
        this.groupService = groupService;
        this.groupMemberService = groupMemberService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "user")
    public ResponseEntity<User> getUserById(@RequestParam(value = "userToken", required = true) String token) {
        if(!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "user/login")
    public ResponseEntity<Login> login(@RequestParam(value = "mail", required = true) String mail,
                                         @RequestParam(value = "password", required = true) String pass) {
        User user = userService.findByEmail(mail);
        if(pass.equals(user.getPassword())) {
            String token = userService.login(user.getId());
            return ResponseEntity.ok(new Login(user.isAdmin(), token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "user/create")
    public ResponseEntity<Boolean> createUser(@RequestParam(value = "name", required = true) String name,
                                     @RequestParam(value = "mail", required = true) String mail,
                                     @RequestParam(value = "isAdmin", required = true) boolean isAdmin,
                                     @RequestParam(value = "password", required = true) String pass,
                                     @RequestParam(value = "language", required = true) String language) {
        userService.addUser(name, mail, isAdmin, pass, language);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.GET, path = "template")
    public ResponseEntity<Set<Template>> getUserTemplates(@RequestParam(value = "userToken") String token) {
        if(!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(user.getTemplates());
    }

    @RequestMapping(method = RequestMethod.GET, path = "template/single")
    public ResponseEntity<Template> getTemplate(@RequestParam(value = "userToken") String token,
                                                          @RequestParam(value = "id") int id) {
        if(!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(templateService.getTemplateById(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "template/create")
    public ResponseEntity<Boolean> createTemplate(@RequestParam(value = "userToken") String token,
                                                  @RequestParam(value = "name") String name,
                                                  @RequestParam(value = "gradeDeviation") double gradeDeviation,
                                                  @RequestParam(value = "isCommentNeeded") boolean isCommentNeeded) {
        if(!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        templateService.addTemplate(name, gradeDeviation, isCommentNeeded, user);

        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.GET, path = "group")
    public ResponseEntity<List<Group>> getUserGroups(@RequestParam(value = "userToken", required = true) String token) {
        if(!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        Set<Template> templates = user.getTemplates();
        List<Group> groups = new ArrayList<Group>();

        for (Template template : templates) {
            Set<Group> templateGroups = template.getGroups();
            for (Group group : templateGroups) {
                groups.add(group);
            }
        }

        return ResponseEntity.ok(groups);
    }

    @RequestMapping(method = RequestMethod.GET, path = "group/single")
    public ResponseEntity<Group> getGroup(@RequestParam(value = "userToken", required = true) String token,
                                          @RequestParam(value = "id") int id) {
        if(!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(groupService.getById(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "group/create")
    public ResponseEntity<Boolean> createGroup(@RequestParam(value = "userToken") String token,
                                               @RequestParam(value = "templateId") int templateId,
                                               @RequestParam(value = "name") String name,
                                               @RequestParam(value = "deadline") String deadline,
                                               @RequestParam(value = "groupGrade") double groupGrade) {
        if(!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date deadlineDate = format.parse(deadline);

            Template template = templateService.getTemplateById(templateId);
            groupService.addGroup(name, new Date(), deadlineDate, groupGrade, template);
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "group/addmember")
    public ResponseEntity<Boolean> createGroupMember(@RequestParam(value = "userToken") String userToken,
                                                     @RequestParam(value = "groupId") int groupId,
                                                     @RequestParam(value = "name") String name,
                                                     @RequestParam(value = "email") String email,
                                                     @RequestParam(value = "token") String token) {
        if(!userService.isLastUserToken(userToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        groupMemberService.createMember(name, email, token, false, groupService.getById(groupId));

        return ResponseEntity.ok(true);
    }
}
