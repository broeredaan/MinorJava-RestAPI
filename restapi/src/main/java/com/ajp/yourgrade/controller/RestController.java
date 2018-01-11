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
    @RequestMapping(method = RequestMethod.GET, path = "addTest")
    public ResponseEntity<String> addUsers(){
        User user = new User("Test Name", "test@mail.com", true, "testPass", "en-EN");
        User user2 = new User("Name Test", "test2@mail.com", false, "testPass", "en-EN");
        Template template = new Template("Test", 2, true, user);
        Template template2 = new Template("Test2", 1, false, user);
        Template template3 = new Template("Test", 2, true, user2);
        Group group = new Group("Test", new Date(), new Date(), 7, template);
        Group group2 = new Group("Test2", new Date(), new Date(), 7, template2);
        Group group3 = new Group("Test", new Date(), new Date(), 7, template3);
        GroupMember member = new GroupMember("Test Name", "test@mail.com", "AG#WHY$JYISRHNESHNESHI#h93", false, group);
        GroupMember member2 = new GroupMember("Name Name", "test2@mail.com", "AG#WHY$JYISRHNESHNESHI#h93", true, group2);
        GroupMember member3 = new GroupMember("Name Test", "test3@mail.com", "AG#WHY$JYISRHNESHNESHI#h93", false, group3);
        Rating rating = new Rating(6, "You suck!", member, member2);
        Rating rating2 = new Rating(7, "You suck!", member2, member);
        Rating rating3 = new Rating(6, "You suck!", member3, member3);

        userRepo.save(user);
        userRepo.save(user2);
        templateRepo.save(template);
        templateRepo.save(template2);
        templateRepo.save(template3);
        groupRepo.save(group);
        groupRepo.save(group2);
        groupRepo.save(group3);
        groupMemberRepo.save(member);
        groupMemberRepo.save(member2);
        groupMemberRepo.save(member3);
        ratingRepo.save(rating);
        ratingRepo.save(rating2);
        ratingRepo.save(rating3);

        return ResponseEntity.ok("Done");
    }

    /**
     * TEST METHOD. Used to test database integration. SHOULD GET REMOVED.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "test")
    public ResponseEntity<List<User>> retrieveUsers(){
        List<User> data = new ArrayList<User>();
        for(User r : userRepo.findAll()){
            data.add((r));
        }

        return ResponseEntity.ok(data);
    }
}
