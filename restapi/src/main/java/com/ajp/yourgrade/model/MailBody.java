package com.ajp.yourgrade.model;

public class MailBody {

    private String userToken;
    private String name;
    private String email;

    //Dummy constructor
    public MailBody(){

    }

    public MailBody(String userToken, String name, String email) {
        this.userToken = userToken;
        this.name = name;
        this.email = email;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
