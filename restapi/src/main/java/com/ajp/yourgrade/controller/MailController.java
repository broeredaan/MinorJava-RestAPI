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


/**
 * The MailController is responsible for all API calls regarding the emails that have to be send.
 */
@RestController
@RequestMapping(path = "v1/mail")
public class MailController {

    //Internal reference to the services used by this class
    private UserService userService;
    private GroupService groupService;
    private MailService mailService;

    //Internal reference to the ConfigProperties class serving the application.properties values
    private ConfigProperties configProperties;

    /**
     * The constructor of the MailController, used for constructor injection
     * @param userService Save the reference to the passed-in userService inside this class
     * @param groupService Save the reference to the passed-in groupService inside this class
     * @param mailService Save the reference to the passed-in mailService inside this class
     * @param configProperties Save the reference to the passed-in configProperties inside this class
     */
    public MailController(@Autowired UserService userService, GroupService groupService, MailService mailService, ConfigProperties configProperties) {
        //Save the passed in references
        this.userService = userService;
        this.groupService = groupService;
        this.mailService = mailService;
        this.configProperties = configProperties;
    }

    /**
     * API call used for sending rating requests in bulk
     * @param body Raw JSON string filled with the group information, based on the MailBody model
     * @param errors Pass errors status before sending the emails
     * @return Boolean Returns true if the call was handled successfully
     */
    @RequestMapping(path = "sendRequest")
    public ResponseEntity<Boolean> createRequest(@RequestBody MailBody body, Errors errors) {

        //If the token is incorrect/outdated prevent access
        if (!userService.isLastUserToken(body.getUserToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        //Error handling
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //Retrieve group information
        Group group = groupService.getById(body.getGroupId());

//        //Already send
//        if(group.isSend()) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//        }

        //Extract the member information from the parent object
        Set<GroupMember> members = group.getGroupMembers();

        //Send the emails
        try {
            for (GroupMember member : members) {
                mailService.sendRequest(member.getEmail(), member.getName(), member.getToken(), configProperties.getSurveyLink(), userService.getUserByToken(body.getUserToken()).getName());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //Update the send status of the group
        groupService.setSend(true, body.getGroupId());

        return ResponseEntity.ok(true);
    }
}
