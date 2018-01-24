package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Rating;
import com.ajp.yourgrade.persistence.RatingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RatingServiceImpl implements RatingService{

    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void addRating(double grade, String comment, GroupMember groupMember, GroupMember ratedMember) {
        ratingRepository.save(new Rating(grade,comment,groupMember,ratedMember));
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
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }
}
