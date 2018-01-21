package com.ajp.yourgrade.persistence;

import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Rating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {

    Rating findById(int id);
    List<Rating> findByGroupMember(GroupMember groupMember);
    List<Rating> findByRatedMember(GroupMember groupMember);
    void deleteById(int id);
}
