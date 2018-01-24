package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.MailBody;
import com.ajp.yourgrade.properties.ConfigProperties;
import com.ajp.yourgrade.service.GroupService;
import com.ajp.yourgrade.service.MailService;
import com.ajp.yourgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "v1/mail")
public class MailController {

    private UserService userService;
    private GroupService groupService;
    private MailService mailService;

    private ConfigProperties configProperties;

    public MailController(@Autowired UserService userService, GroupService groupService, MailService mailService, ConfigProperties configProperties) {
        this.userService = userService;
        this.groupService = groupService;
        this.mailService = mailService;
        this.configProperties = configProperties;
    }

    @RequestMapping(path = "sendRequest")
    public ResponseEntity<Boolean> createRequest(@RequestBody MailBody body, Errors errors) {

        if (!userService.isLastUserToken(body.getUserToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        //Error handling
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Group group = groupService.getById(body.getGroupId());

//        //Already send
//        if(group.isSend()) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//        }

        Set<GroupMember> members = group.getGroupMembers();

        try {
            for (GroupMember member : members) {
                mailService.sendRequest(member.getEmail(), member.getName(), member.getToken(), configProperties.getSurveyLink(), userService.getUserByToken(body.getUserToken()).getName());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        groupService.setSend(true, body.getGroupId());

        return ResponseEntity.ok(true);
    }
}
