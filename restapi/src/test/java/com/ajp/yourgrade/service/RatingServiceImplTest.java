package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Rating;
import com.ajp.yourgrade.persistence.RatingRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingServiceImpl;

    @Mock
    private RatingServiceImpl ratingServiceImplMock;

    @Mock
    private GroupMember groupMemberMock;

    @Mock
    private GroupMember groupMemberMock2;

    private Rating rating = new Rating(6.0,"TESTING",groupMemberMock,groupMemberMock2);


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    //This method checks if the function addRating is called the appropriate amount of times.
    @Test
    public void addRating() {
        //Calls the method.
        ratingServiceImplMock.addRating(8.0, "Testing", groupMemberMock, groupMemberMock);
        //Checks how many times the method has been called.
        verify(ratingServiceImplMock, only()).addRating(8.0, "Testing", groupMemberMock, groupMemberMock);
    }

    /**
     * Removed
     */
//    //This method checks if the function deleteRating is called the appropriate amount of times.
//    @Test
//    public void deleteRating() {
//        //Calls the method.
//        ratingServiceImplMock.deleteRating(0);
//        //Checks how many times the method has been called.
//        verify(ratingServiceImplMock, only()).deleteRating(0);
//    }

    //This method checks if the function saveRating is called the appropriate amount of times.
    @Test
    public void saveRating() {
        //Calls the method.
        ratingServiceImplMock.saveRating(rating);
        //Checks how many times the method has been called.
        verify(ratingServiceImplMock, only()).saveRating(rating);
    }

    /**
     * Removed
     */
//    //This method tests the return values of the method getById.
//    @Test
//    public void getById() {
//        //Give value that will be returned when a rating is searched in the repo (id)
//        when(ratingRepository.findById(0)).thenReturn(rating);
//        //Get a rating from the service
//        Rating result = ratingServiceImpl.getById(0);
//        //Check if the values are correct.
//        assertEquals(0,result.getId());
//        assertEquals(6.0, result.getGrade());
//        assertEquals("TESTING", result.getComment());
//        assertEquals(null, result.getGroupMember());
//        assertEquals(null, result.getRatedMember());
//    }

    //This method tests the return values of the method getByGroupMember.
    @Test
    public void getByGroupMember() {
        //Create a List so it can be returned by the Mock class.
        List<Rating> ratingList = new ArrayList<Rating>();
        ratingList.add(rating);
        //Give value that will be returned when a rating is searched in the repo (GroupMember).
        when(ratingRepository.findByGroupMember(groupMemberMock)).thenReturn(ratingList);
        //Get a template from the service then add it to the List.
        List<Rating> result = ratingServiceImpl.getByGroupMember(groupMemberMock);
        //Check if the values are correct.
        for (Rating r : result) {
            assertEquals(0,r.getId());
            assertEquals(6.0, r.getGrade());
            assertEquals("TESTING", r.getComment());
            assertEquals(null, r.getGroupMember());
            assertEquals(null, r.getRatedMember());
        }
    }

    //This method tests the return values of the method getByRatedMember.
    @Test
    public void getByRatedMember() {
        //Create a List so it can be returned by the Mock class.
        List<Rating> ratingList = new ArrayList<Rating>();
        ratingList.add(rating);
        //Give value that will be returned when a rating is searched in the repo (RatedMember).
        when(ratingRepository.findByRatedMember(groupMemberMock2)).thenReturn(ratingList);
        //Get a template from the service then add it to the List.
        List<Rating> result = ratingServiceImpl.getByGroupMember(groupMemberMock2);
        //Check if the values are correct.
        for (Rating r : result) {
            assertEquals(0,r.getId());
            assertEquals(6.0, r.getGrade());
            assertEquals("TESTING", r.getComment());
            assertEquals(groupMemberMock, r.getGroupMember());
            assertEquals(groupMemberMock2, r.getRatedMember());
        }
    }
}