package com.ajp.yourgrade.model;

import com.ajp.yourgrade.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class RatingInfo {

    @JsonView(View.Public.class)
    private double groupGrade;

    @JsonView(View.Public.class)
    private boolean isCommentNeeded;

    @JsonView(View.Public.class)
    private double maxGradeDifference;

    @JsonView(View.Public.class)
    private List<Rating> ratings;

    protected RatingInfo() {

    }

    public RatingInfo(double groupGrade, boolean isCommentNeeded, double maxGradeDifference, List<Rating> ratings) {
        this.groupGrade = groupGrade;
        this.isCommentNeeded = isCommentNeeded;
        this.maxGradeDifference = maxGradeDifference;
        this.ratings = ratings;
    }

    public double getGroupGrade() {
        return groupGrade;
    }

    public void setGroupGrade(double groupGrade) {
        this.groupGrade = groupGrade;
    }

    public boolean isCommentNeeded() {
        return isCommentNeeded;
    }

    public void setCommentNeeded(boolean commentNeeded) {
        isCommentNeeded = commentNeeded;
    }

    public double getMaxGradeDifference() {
        return maxGradeDifference;
    }

    public void setMaxGradeDifference(double maxGradeDifference) {
        this.maxGradeDifference = maxGradeDifference;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
