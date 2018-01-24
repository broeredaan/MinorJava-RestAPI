package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.View;
import com.ajp.yourgrade.model.*;
import com.ajp.yourgrade.service.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "v1/group")
public class GroupController {

    private UserService userService;
    private GroupService groupService;
    private TemplateService templateService;
    private GroupMemberService groupMemberService;
    private ApproveService approveService;

    public GroupController(@Autowired UserService userService, GroupService groupService, TemplateService templateService,
                           GroupMemberService groupMemberService, ApproveService approveService) {
        this.userService = userService;
        this.groupService = groupService;
        this.templateService = templateService;
        this.groupMemberService = groupMemberService;
        this.approveService = approveService;
    }

    @JsonView(View.Teacher.class)
    @RequestMapping(method = RequestMethod.GET, path = "")
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

    @JsonView(View.Teacher.class)
    @RequestMapping(method = RequestMethod.GET, path = "single")
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

    @RequestMapping(method = RequestMethod.PUT, path = "create")
    public ResponseEntity<Boolean> createGroup(@RequestBody GroupBody body) {
        if (!userService.isLastUserToken(body.getToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date deadlineDate = format.parse(body.getDeadline());

            Template template = templateService.getTemplateById(body.getTemplateId());
            int groupId = groupService.addGroup(body.getName(), new Date(), deadlineDate, body.getGroupGrade(), template, false);

            for (GroupMemberBody member : body.getMembers()) {
                groupMemberService.createMember(member.getName(), member.getEmail(),
                        false, groupService.getById(groupId));
            }

        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(true);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "delete")
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


    @RequestMapping(method = RequestMethod.PUT, path = "approve")
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

    @RequestMapping(method = RequestMethod.GET, path = ".pdf")
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
}
