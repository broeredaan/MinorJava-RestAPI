package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Rating;

import java.util.List;

public interface RatingService {
    Rating getById(int id);
    List<Rating> getByGroupMember(GroupMember groupmember);
    List<Rating> GetByRatedMember(GroupMember groupMember);
    Rating findById(int id);
}
