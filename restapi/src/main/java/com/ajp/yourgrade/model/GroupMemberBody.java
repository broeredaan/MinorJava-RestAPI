package com.ajp.yourgrade.model;

public class GroupMemberBody {

    private String email;
    private String name;
    private String token;

    //Dummy constructor
    public GroupMemberBody() {

    }

    public GroupMemberBody(String email, String name, String token) {
        this.email = email;
        this.name = name;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
