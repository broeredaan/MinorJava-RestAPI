package com.ajp.yourgrade.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController

@RequestMapping("api")
public class RestController {

    /**
     *
     */
    public RestController() {

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
        return ResponseEntity.ok("Hello");
    }

}
