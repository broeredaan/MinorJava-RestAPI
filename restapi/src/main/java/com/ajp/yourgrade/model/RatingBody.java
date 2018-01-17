package com.ajp.yourgrade.model;

public class RatingBody {
    private String token;
    private int ratedMemberId;
    private double grade;
    private String comment;

    public RatingBody() {
    }

    public RatingBody(String token, int ratedMemberId, double grade, String comment) {
        this.token = token;
        this.ratedMemberId = ratedMemberId;
        this.grade = grade;
        this.comment = comment;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getRatedMemberId() {
        return ratedMemberId;
    }

    public void setRatedMemberId(int ratedMemberId) {
        this.ratedMemberId = ratedMemberId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
