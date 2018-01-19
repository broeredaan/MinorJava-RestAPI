package com.ajp.yourgrade.model;

public class TemplateBody {

    private String userToken;
    private String name;
    private double gradeDeviation;
    private boolean isCommentNeeded;

    //Dummy constructor
    public TemplateBody() {

    }

    public TemplateBody(String userToken, String name, double gradeDeviation, boolean isCommentNeeded) {
        this.userToken = userToken;
        this.name = name;
        this.gradeDeviation = gradeDeviation;
        this.isCommentNeeded = isCommentNeeded;
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

    public double getGradeDeviation() {
        return gradeDeviation;
    }

    public void setGradeDeviation(double gradeDeviation) {
        this.gradeDeviation = gradeDeviation;
    }

    public boolean getIsCommentNeeded() {
        return isCommentNeeded;
    }

    public void setCommentNeeded(boolean commentNeeded) {
        isCommentNeeded = commentNeeded;
    }
}
