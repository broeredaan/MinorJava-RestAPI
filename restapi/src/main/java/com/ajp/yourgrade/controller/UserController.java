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

/**
 * The UserController handles all the Calls for the User management
 */
@RestController
@RequestMapping(path = "v1/user")
public class UserController {

    //Internal reference to the services used by this class
    private UserService userService;

    /**
     * The constructor of the GroupController, used for constructor injection
     * @param userService Save the reference to the passed-in userService inside this class
     */
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }


    /**
     * API to get an overview the user information
     * @param token RequestParam "userToken" retrieved after first login
     * @return User Return the user information
     */
    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<User> getUserByToken(@RequestParam(value = "userToken") String token) {
        if (!userService.isLastUserToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(user);
    }

    /**
     * API Call used for the login
     * @param body Raw JSON string filled with the login credentials, based on the LoginBody model
     * @return Login Returns an authentication token in case of a successful login
     */
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

    /**
     * API Call used to create a new user
     * @param body Raw JSON string filled with the user information, based on the UserBody model
     * @return Boolean Returns true if the process went successfully
     */
    @RequestMapping(method = RequestMethod.PUT, path = "create")
    public ResponseEntity<Boolean> createUser(@RequestBody UserBody body) {
        if(!userService.getUserByToken(body.getUserToken()).isAdmin()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        userService.addUser(body.getName(), body.getEmail(), body.getIsAdmin(), body.getPassword(), body.getLanguage());
        return ResponseEntity.ok(true);
    }
}
