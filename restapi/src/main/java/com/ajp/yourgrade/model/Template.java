package com.ajp.yourgrade.model;

public class Template {
    private int id;
    private int userId;
    private String name;
    private double gradeDeviation;
    private boolean isCommentNeeded;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public boolean isCommentNeeded() {
        return isCommentNeeded;
    }

    public void setCommentNeeded(boolean commentNeeded) {
        isCommentNeeded = commentNeeded;
    }
}
