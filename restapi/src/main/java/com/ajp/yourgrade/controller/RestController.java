package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.model.*;
import com.ajp.yourgrade.persistence.*;
import com.ajp.yourgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("v1")
public class RestController {

    private UserService userService;

    /**
     *
     */
    public RestController(@Autowired UserService userService) {
        this.userService = userService;
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

    @RequestMapping(method = RequestMethod.GET, path = "user")
    public ResponseEntity<User> getUserById(@RequestParam(value = "id", required = true) int id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "user/login")
    public ResponseEntity<Boolean> login(@RequestParam(value = "mail", required = true) String mail,
                                         @RequestParam(value = "password", required = true) String pass) {
        User user = userService.findByEmail(mail);
        if(pass == user.getPassword()) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "user/create")
    public ResponseEntity<Boolean> createUser(@RequestParam(value = "name", required = true) String name,
                                     @RequestParam(value = "mail", required = true) String mail,
                                     @RequestParam(value = "isAdmin", required = true) boolean isAdmin,
                                     @RequestParam(value = "password", required = true) String pass,
                                     @RequestParam(value = "language", required = true) String language) {
        userService.createUser(name, mail, isAdmin, pass, language);
        return ResponseEntity.ok(true);
    }
}
