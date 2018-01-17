package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Rating;

import java.util.List;

public interface RatingService {
    void addRating(double grade, String comment, GroupMember groupMember, GroupMember ratedMember);
    void deleteRating(int id);
    Rating getById(int id);
    List<Rating> getByGroupMember(GroupMember groupmember);
    List<Rating> GetByRatedMember(GroupMember groupMember);
    void saveRating(Rating rating);
}
