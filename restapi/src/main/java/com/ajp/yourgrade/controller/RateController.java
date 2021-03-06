package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.View;
import com.ajp.yourgrade.model.*;
import com.ajp.yourgrade.service.GroupMemberService;
import com.ajp.yourgrade.service.RatingService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The RateController is responsible for all API calls regarding the evaluation of group members
 */
@RestController
@RequestMapping(path = "v1/rate")
public class RateController {

    //Internal reference to the services used by this class
    private GroupMemberService groupMemberService;
    private RatingService ratingService;

    /**
     * The constructor of the GroupController, used for constructor injection
     * @param groupMemberService Save the reference to the passed-in groupMemberService inside this class
     * @param ratingService Save the reference to the passed-in groupMemberService inside this class
     */
    public RateController(@Autowired GroupMemberService groupMemberService, RatingService ratingService) {
        //Save the passed in references
        this.groupMemberService = groupMemberService;
        this.ratingService = ratingService;
    }

    /**
     * API call to get all the relevant information to rate a group
     * @param token RequestParam "token" retrieved after first login
     * @return RatingInfo Returns a raw Json string containing the relevant data, based on the RatingInfo model
     */
    @JsonView(View.Public.class)
    @RequestMapping(method = RequestMethod.GET, path = "start")
    public ResponseEntity<RatingInfo> startRateMembers(@RequestParam(value = "token") String token) {

        //Retrieve member info, who is going to submit
        GroupMember ratingMember = groupMemberService.getMemberByToken(token);

        //Retrieve the appropriate group information
        Group group = ratingMember.getGroup();

        //Retrieve the template that belongs to the group
        Template template = group.getTemplate();

        //Already submitted, prevent access
        if(ratingMember.isHasSubmitted()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        //Check if a rating already exists
        for (GroupMember member : group.getGroupMembers()) {
            boolean ratingExists = false;
            for (Rating rating : ratingService.getByGroupMember(ratingMember)) {
                if (rating.getRatedMember().getId() == member.getId()) {
                    ratingExists = true;
                }
            }

            //if not, fill in defaults for initial use
            if(!ratingExists) {
                ratingService.addRating(group.getGroupGrade(), "", ratingMember, member);
            }
        }

        //Prepare data
        RatingInfo ratingInfo = new RatingInfo(group.getGroupGrade(), template.isCommentNeeded(), template.getGradeDeviation(), ratingService.getByGroupMember(ratingMember));

        //Return the rating data
        return ResponseEntity.ok(ratingInfo);
    }

    /**
     * API call for submitting the group rating
     * @param body Raw JSON string filled with the rating information, based on the RatingBody model
     * @return Boolean Returns true if the call was handled successfully
     */
    @RequestMapping(method = RequestMethod.POST, path = "finish")
    public ResponseEntity<Boolean> rateMembers(@RequestBody RatingBody body) {

        //Get the member who is rating
        GroupMember ratingMember = groupMemberService.getMemberByToken(body.getToken());

        //Stop here in case of already submitted
        if(ratingMember.isHasSubmitted() == true) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        //Get the to be updated list of ratings
        List<Rating> toBeUpdated = ratingService.getByGroupMember(ratingMember);

        //Get the list of ratings
        List<Rating> ratings = body.getRatings();

        //Update the data
        for(Rating toBeUpdatedRating : toBeUpdated) {
            for(Rating rating : ratings) {
                if(toBeUpdatedRating.getId() == rating.getId()) {
                    toBeUpdatedRating.setComment(rating.getComment());
                    toBeUpdatedRating.setGrade(rating.getGrade());
                    ratingService.saveRating(toBeUpdatedRating);
                }
            }
        }

        //Update the member status to submitted
        if(!ratingMember.isHasSubmitted()) {
            ratingMember.setHasSubmitted(true);
            groupMemberService.saveMember(ratingMember);
        }

        return ResponseEntity.ok(true);
    }
}
