package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.View;
import com.ajp.yourgrade.model.*;
import com.ajp.yourgrade.properties.ConfigProperties;
import com.ajp.yourgrade.service.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    private ApproveService approveService;

    /**
     *
     */
    public RestController(@Autowired UserService userService, TemplateService templateService, GroupService groupService, GroupMemberService groupMemberService, RatingService ratingService, ApproveService approveService) {
        this.userService = userService;
        this.templateService = templateService;
        this.groupService = groupService;
        this.groupMemberService = groupMemberService;
        this.ratingService = ratingService;
        this.approveService = approveService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "user")
    public ResponseEntity<User> getUserById(@RequestParam(value = "userToken") String token) {
        if (!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "user/login")
    public ResponseEntity<Login> login(@RequestParam(value = "mail") String mail,
                                       @RequestParam(value = "password") String pass) {
        User user = userService.findByEmail(mail);
        if (pass.equals(user.getPassword())) {
            String token = userService.login(user.getId());
            return ResponseEntity.ok(new Login(user.isAdmin(), token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "user/create")
    public ResponseEntity<Boolean> createUser(@RequestBody UserBody body) {
        if(!userService.getUserByToken(body.getUserToken()).isAdmin()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        userService.addUser(body.getName(), body.getEmail(), body.getIsAdmin(), body.getPassword(), body.getLanguage());
        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.GET, path = "template")
    public ResponseEntity<Set<Template>> getUserTemplates(@RequestParam(value = "userToken") String token) {
        if (!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(user.getTemplates());
    }

    @RequestMapping(method = RequestMethod.GET, path = "template/single")
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

    @RequestMapping(method = RequestMethod.PUT, path = "template/create")
    public ResponseEntity<Boolean> createTemplate(@RequestBody TemplateBody body) {
        if (!userService.isLastUserToken(body.getUserToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(body.getUserToken());
        templateService.addTemplate(body.getName(), body.getGradeDeviation(), body.getIsCommentNeeded(), user);

        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "template/delete")
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

    @RequestMapping(method = RequestMethod.GET, path = "group")
    public ResponseEntity<List<Group>> getUserGroups(@RequestParam(value = "userToken") String token) {
        if (!userService.isLastUserToken(token)) {
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
        if (!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Group group = groupService.getById(id);
        if(group.getTemplate().getUser().getId() != userService.getUserByToken(token).getId()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(group);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "group/create")
    public ResponseEntity<Boolean> createGroup(@RequestBody GroupBody body) {
        if (!userService.isLastUserToken(body.getToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date deadlineDate = format.parse(body.getDeadline());

            Template template = templateService.getTemplateById(body.getTemplateId());
            int groupId = groupService.addGroup(body.getName(), new Date(), deadlineDate, body.getGroupGrade(), template);

            for (GroupMemberBody member : body.getMembers()) {
                groupMemberService.createMember(member.getName(), member.getEmail(),
                        false, groupService.getById(groupId));
            }
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "group/delete")
    public ResponseEntity<Boolean> deleteGroup(@RequestParam(value = "userToken") String token,
                                               @RequestParam(value = "groupId") int id) {
        if (!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Group group = groupService.getById(id);
        if(group.getTemplate().getUser().getId() != userService.getUserByToken(token).getId()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        groupService.deleteGroup(id);

        return ResponseEntity.ok(true);
    }




    @RequestMapping(method = RequestMethod.PUT, path = "group/approve")
    public ResponseEntity<Boolean> approveGroup(@RequestParam(value = "id") int id,
                                                @RequestParam(value = "userToken") String userToken) throws IOException {
        if(groupService.getById(id).getTemplate().getUser().getId() != userService.getUserByToken(userToken).getId()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        approveService.approve(id);
        approveService.setFinalGrade(id);
        approveService.createPdf(id);

        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.GET, path = "group/pdf")
    public   ResponseEntity<InputStreamResource> getPdf(@RequestParam(value = "id") int id,
                                                        @RequestParam(value = "userToken") String userToken) throws IOException {
        if(groupService.getById(id).getTemplate().getUser().getId() != userService.getUserByToken(userToken).getId()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        File file = new File("download/" + id + ".pdf");
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.valueOf("application/pdf"));
        respHeaders.setContentDispositionFormData("attachment", id + ".pdf");
        InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
        return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);

    }

    @JsonView(View.Public.class)
    @RequestMapping(method = RequestMethod.GET, path = "rate/start")
    public ResponseEntity<RatingInfo> startRateMembers(@RequestParam(value = "token") String token) {

        //Retrieve member info, who is going to submit
        GroupMember ratingMember = groupMemberService.getMemberByToken(token);

        //Retrieve the appropriate group information
        Group group = ratingMember.getGroup();


        //Retrieve the template that belongs to the group
        Template template = group.getTemplate();

        //Already submitted, prevent access
        if(ratingMember.isHasSubmitted()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        //Check if a rating already exists
        for (GroupMember member : group.getGroupMembers()) {
            boolean ratingExists = false;
            for (Rating rating : ratingService.getByGroupMember(ratingMember)) {
                if (rating.getRatedMember().getId() == member.getId()) {
                    ratingExists = true;
                }
            }

            //if not, fill in defaults for initial use
            if(!ratingExists) {
                ratingService.addRating(group.getGroupGrade(), "", ratingMember, member);
            }
        }

        //Prepare data
        RatingInfo ratingInfo = new RatingInfo(group.getGroupGrade(), template.isCommentNeeded(), template.getGradeDeviation(), ratingService.getByGroupMember(ratingMember));

        //Return the rating data
        return ResponseEntity.ok(ratingInfo);
    }

    @RequestMapping(method = RequestMethod.POST, path = "rate/finish")
    public ResponseEntity<Boolean> rateMembers(@RequestBody RatingBody body) {

        //Get the member who is rating
        GroupMember ratingMember = groupMemberService.getMemberByToken(body.getToken());

        //Stop here in case of already submitted
        if(ratingMember.isHasSubmitted() == true) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        //Get the to be updated list of ratings
        List<Rating> toBeUpdated = ratingService.getByGroupMember(ratingMember);

        //Get the list of ratings
        List<Rating> ratings = body.getRatings();

        //Update the data
        for(Rating toBeUpdatedRating : toBeUpdated) {

            for(Rating rating : ratings) {

                if(toBeUpdatedRating.getId() == rating.getId()) {
                    toBeUpdatedRating.setComment(rating.getComment());
                    toBeUpdatedRating.setGrade(rating.getGrade());
                    ratingService.saveRating(toBeUpdatedRating);
                }

            }

        }

//        for (RatingBody item : body) {
//
//            GroupMember member = groupMemberService.getMemberByToken(item.getToken());
//            List<Rating> ratings = ratingService.getByGroupMember(member);
//
//            if (!member.isHasSubmitted()) {
//                member.setHasSubmitted(true);
//                groupMemberService.saveMember(member);
//            }
//
//            for (Rating rating : ratings) {
//                if (rating.getRatedMember().getId() == item.getRatedMemberId()) {
//                    rating.setGrade(item.getGrade());
//                    rating.setComment(item.getComment());
//                    ratingService.saveRating(rating);
//                }
//            }
//        }

        //Update the member status to submitted
        if(!ratingMember.isHasSubmitted()) {
            ratingMember.setHasSubmitted(true);
            groupMemberService.saveMember(ratingMember);
        }

        return ResponseEntity.ok(true);
    }

    @Autowired
    private MailService mailService;

    @Autowired
    private ConfigProperties configProperties;

    @RequestMapping(path = "mail/sendRequest")
    public ResponseEntity<Boolean> createRequest(@RequestBody MailBody body, Errors errors) {

        if (!userService.isLastUserToken(body.getUserToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        //Error handling
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        mailService.sendRequest(body.getEmail(), body.getName(), groupMemberService.getTokenByEmail(body.getEmail()), configProperties.getSurveyLink(), userService.getUserByToken(body.getUserToken()).getName());
        return ResponseEntity.ok(true);
    }

}
