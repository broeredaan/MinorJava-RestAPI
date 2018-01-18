package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.model.*;
import com.ajp.yourgrade.properties.ConfigProperties;
import com.ajp.yourgrade.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private RatingService ratingService;

    /**
     *
     */
    public RestController(@Autowired UserService userService, TemplateService templateService, GroupService groupService, GroupMemberService groupMemberService, RatingService ratingService) {
        this.userService = userService;
        this.templateService = templateService;
        this.groupService = groupService;
        this.groupMemberService = groupMemberService;
        this.ratingService = ratingService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "user")
    public ResponseEntity<User> getUserById(@RequestParam(value = "userToken") String token) {
        if(!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "user/login")
    public ResponseEntity<Login> login(@RequestParam(value = "mail") String mail,
                                         @RequestParam(value = "password") String pass) {
        User user = userService.findByEmail(mail);
        if(pass.equals(user.getPassword())) {
            String token = userService.login(user.getId());
            return ResponseEntity.ok(new Login(user.isAdmin(), token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "user/create")
    public ResponseEntity<Boolean> createUser(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "mail") String mail,
                                     @RequestParam(value = "isAdmin") boolean isAdmin,
                                     @RequestParam(value = "password") String pass,
                                     @RequestParam(value = "language") String language) {
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

    @RequestMapping(method = RequestMethod.DELETE, path = "template/delete")
    public ResponseEntity<Boolean> deleteTemplate(@RequestParam(value = "userToken") String token,
                                                  @RequestParam(value = "templateId") int id) {
        if(!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        templateService.deleteTemplate(id);

        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.GET, path = "group")
    public ResponseEntity<List<Group>> getUserGroups(@RequestParam(value = "userToken") String token) {
        if(!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        Set<Template> templates = user.getTemplates();
        List<Group> groups = new ArrayList<Group>();

        for (Template template : templates) {
            Set<Group> templateGroups = template.getGroups();
            groups.addAll(templateGroups);
        }

        return ResponseEntity.ok(groups);
    }

    @RequestMapping(method = RequestMethod.GET, path = "group/single")
    public ResponseEntity<Group> getGroup(@RequestParam(value = "userToken") String token,
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
                                                     @RequestParam(value = "email") String email) {
        if(!userService.isLastUserToken(userToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        groupMemberService.createMember(name, email, "", false, groupService.getById(groupId));

        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.GET, path = "rate/start")
    public ResponseEntity<List<Rating>> startRateMembers(@RequestParam(value = "token") String token) {
        GroupMember ratingMember = groupMemberService.getMemberByToken(token);
        Group group = ratingMember.getGroup();

        if(ratingMember.isHasSubmitted()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        for (GroupMember member : group.getGroupMembers()) {
            boolean ratingExists = false;
            for (Rating rating : ratingService.getByGroupMember(ratingMember)) {
                if(rating.getRatedMember().getId() == member.getId()) {
                    ratingExists = true;
                }
            }

            if(!ratingExists) {
                ratingService.addRating(group.getGroupGrade(), "", ratingMember, member);
            }
        }

        return ResponseEntity.ok(ratingService.getByGroupMember(ratingMember));
    }

    @RequestMapping(method = RequestMethod.POST, path = "rate/finish")
    public ResponseEntity<Boolean> rateMembers(@RequestBody List<RatingBody> body) {
        for (RatingBody item : body) {
            GroupMember member = groupMemberService.getMemberByToken(item.getToken());
            List<Rating> ratings = ratingService.getByGroupMember(member);

            if(!member.isHasSubmitted()) {
                member.setHasSubmitted(true);
                groupMemberService.saveMember(member);
            }

            for (Rating rating : ratings) {
                if(rating.getRatedMember().getId() == item.getRatedMemberId()) {
                    rating.setGrade(item.getGrade());
                    rating.setComment(item.getComment());
                    ratingService.saveRating(rating);
                }
            }
        }

        return ResponseEntity.ok(true);
    }

    @Autowired
    public MailService mailService;

    @Autowired
    private ConfigProperties configProperties;

    @RequestMapping(path = "mail/sendRequest")
    public ResponseEntity<String> createRequest(@ModelAttribute("mailObject") @Valid MailObject mailObject, Errors errors) {

        if(!userService.isLastUserToken(mailObject.getUserToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        //Error handling
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        mailService.sendRequest(mailObject.getTo(), mailObject.getName(), groupMemberService.getTokenByEmail(mailObject.getTo()), configProperties.getSurveyLink(), userService.getUserByToken(mailObject.getUserToken()).getName());
        return ResponseEntity.ok("Success");
    }

}
