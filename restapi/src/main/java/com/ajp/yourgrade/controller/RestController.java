package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.model.*;
import com.ajp.yourgrade.persistence.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api")
public class RestController {

    private UserRepository userRepo;
    private TemplateRepository templateRepo;
    private GroupRepository groupRepo;
    private GroupMemberRepository groupMemberRepo;
    private RatingRepository ratingRepo;

    /**
     *
     */
    public RestController(UserRepository userRepo, TemplateRepository templateRepo, GroupRepository groupRepo, GroupMemberRepository groupMemberRepo, RatingRepository ratingRepo) {
        this.userRepo = userRepo;
        this.templateRepo = templateRepo;
        this.groupRepo = groupRepo;
        this.groupMemberRepo = groupMemberRepo;
        this.ratingRepo = ratingRepo;
    }

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "health")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Operational");
    }

    @RequestMapping(path = "echo")
    public ResponseEntity<String> echo(@RequestParam(value = "msg", required = false) String text) {
        String value = (text != null ? text : "Dit is default tekst");
        return ResponseEntity.ok("helleu");
    }

    /**
     * TEST METHOD. Used to test database integration. SHOULD GET REMOVED.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "test")
    public ResponseEntity<List<String>> addUsers(){
        User user = new User("Test Name", "test@mail.com", true, "testPass", "en-EN");
        Template template = new Template("Test", 2, true, user);
//        Group group = new Group("Test", new Date(), new Date(), 7, template);
//        GroupMember member = new GroupMember("Test Name", "test@mail.com", "AG#WHY$JYISRHNESHNESHI#h93", false, group);
//        Rating rating = new Rating(6, "You suck!", member, member);

        userRepo.save(user);
        templateRepo.save(template);
//        groupRepo.save(group);
//        groupMemberRepo.save(member);
//        ratingRepo.save(rating);

        List<String> data = new ArrayList<String>();
        for(User u : userRepo.findAll()){
            data.add(u.toString());
        }
        for(Template t : templateRepo.findAll()){
            data.add((t.toString()));
        }
//        for(Group g : groupRepo.findAll()){
//            data.add((g.toString()));
//        }
//        for(GroupMember gm : groupMemberRepo.findAll()){
//            data.add((gm.toString()));
//        }
//        for(Rating r : ratingRepo.findAll()){
//            data.add((r.toString()));
//        }

        return ResponseEntity.ok(data);
    }
}
