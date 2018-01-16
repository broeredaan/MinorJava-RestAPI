package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.model.*;
import com.ajp.yourgrade.persistence.*;
import com.ajp.yourgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(method = RequestMethod.GET, path = "user")
    public ResponseEntity<User> getUserById(@RequestParam(value = "id", required = true) int id) {
        User user = userService.getUserById(id);
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

    @RequestMapping(method = RequestMethod.POST, path = "user/create")
    public ResponseEntity<Boolean> createUser(@RequestParam(value = "name", required = true) String name,
                                     @RequestParam(value = "mail", required = true) String mail,
                                     @RequestParam(value = "isAdmin", required = true) boolean isAdmin,
                                     @RequestParam(value = "password", required = true) String pass,
                                     @RequestParam(value = "language", required = true) String language) {
        userService.addUser(name, mail, isAdmin, pass, language);
        return ResponseEntity.ok(true);
    }
}
