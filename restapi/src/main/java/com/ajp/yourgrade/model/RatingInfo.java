package com.ajp.yourgrade.model;

import com.ajp.yourgrade.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

public class RatingInfo {

    @JsonView(View.Public.class)
    private double groupGrade;

    @JsonView(View.Public.class)
    private boolean isCommentNeeded;

    @JsonView(View.Public.class)
    private double maxGradeDifference;

    @JsonView(View.Public.class)
    private Set<Rating> ratings;

    protected RatingInfo() {

    }

    public RatingInfo(double groupGrade, boolean isCommentNeeded, double maxGradeDifference, Set<Rating> ratings) {
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

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
