package com.ajp.yourgrade.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

public class MailObject {
    @Email
    @NotNull
    private String to;
    private String name;
    private String userToken;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
