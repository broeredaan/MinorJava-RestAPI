package com.ajp.yourgrade.model;

public class UserBody {

    private String name;
    private String email;
    private boolean isAdmin;
    private String password;
    private String language;

    //Dummy constructor
    public UserBody() {

    }

    public UserBody(String name, String email, boolean isAdmin, String password, String language) {
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
        this.password = password;
        this.language = language;
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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
