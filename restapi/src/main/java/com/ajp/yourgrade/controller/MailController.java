//Removed

//package com.ajp.yourgrade.controller;
//
//import com.ajp.yourgrade.model.MailObject;
//import com.ajp.yourgrade.service.MailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/mail")
//public class MailController {
//
//    @Autowired
//    public MailService mailService;
//
//    @RequestMapping(path = "sendRequest")
//    public ResponseEntity<String> createRequest(@ModelAttribute("mailObject") @Valid MailObject mailObject, Errors errors) {
//
//        //Error handling
//        if(errors.hasErrors()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        mailService.sendRequest(mailObject.getTo(), mailObject.getName());
//        return ResponseEntity.ok("Success");
//    }
//}