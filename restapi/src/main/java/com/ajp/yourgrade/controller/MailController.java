package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.model.MailObject;
import com.ajp.yourgrade.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    public MailServiceImpl mailService;

    @RequestMapping(path = "send")
    public ResponseEntity<String> createMail(@ModelAttribute("mailObject") @Valid MailObject mailObject, Errors errors) {

        //Error handling
        if(errors.hasErrors()) {
            return ResponseEntity.ok("Error");
        }

        mailService.sendSimpleMessage(mailObject.getTo(), "sendSimpleMail", "SendSimpleMail");
        return ResponseEntity.ok("Success");
    }

    @RequestMapping(path = "sendRequest")
    public ResponseEntity<String> createRequest(@ModelAttribute("mailObject") @Valid MailObject mailObject, Errors errors) {

        //Error handling
        if(errors.hasErrors()) {
            return ResponseEntity.ok("Error");
        }

        mailService.sendRequest(mailObject.getTo(), mailObject.getName());
        return ResponseEntity.ok("Success");
    }
}