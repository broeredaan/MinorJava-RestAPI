package com.ajp.yourgrade.model;

import java.util.List;

public class RatingBody {
    private String token;
    private List<Rating> ratings;

    //Dummy constructor
    public RatingBody() {
    }

    public RatingBody(String token, List<Rating> ratings) {
        this.token = token;
        this.ratings = ratings;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
