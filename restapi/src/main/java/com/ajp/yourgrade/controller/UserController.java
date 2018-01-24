package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.model.Login;
import com.ajp.yourgrade.model.LoginBody;
import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.model.UserBody;
import com.ajp.yourgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/user")
public class UserController {
    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<User> getUserByToken(@RequestParam(value = "userToken") String token) {
        if (!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "login")
    public ResponseEntity<Login> login(@RequestBody LoginBody body) {
        User user = userService.findByEmail(body.getMail());
        if (body.getPassword().equals(user.getPassword())) {
            String token = userService.login(user.getId());
            return ResponseEntity.ok(new Login(user.isAdmin(), token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "create")
    public ResponseEntity<Boolean> createUser(@RequestBody UserBody body) {
        if(!userService.getUserByToken(body.getUserToken()).isAdmin()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        userService.addUser(body.getName(), body.getEmail(), body.getIsAdmin(), body.getPassword(), body.getLanguage());
        return ResponseEntity.ok(true);
    }
}
