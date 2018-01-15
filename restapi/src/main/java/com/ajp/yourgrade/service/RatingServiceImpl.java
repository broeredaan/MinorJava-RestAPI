package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Rating;
import com.ajp.yourgrade.persistence.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating getById(int id) {
       return ratingRepository.findById(id);
    }

    @Override
    public List<Rating> getByGroupMember(GroupMember groupmember) {
        return ratingRepository.findByGroupMember(groupmember);
    }

    @Override
    public List<Rating> GetByRatedMember(GroupMember groupMember) {
        return ratingRepository.findByRatedMember(groupMember);
    }

    @Override
    public Rating findById(int id) {
        return null;
    }
}
