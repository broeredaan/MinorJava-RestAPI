package com.ajp.yourgrade.model;

public class Login {
    private boolean isAdmin;
    private String token;

    public Login(boolean isAdmin, String token) {
        this.isAdmin = isAdmin;
        this.token = token;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
