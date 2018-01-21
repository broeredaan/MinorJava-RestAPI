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

    @Test
    public void addRating() {
        ratingServiceImplMock.addRating(8.0, "Testing", groupMemberMock, groupMemberMock);
        verify(ratingServiceImplMock, only()).addRating(8.0, "Testing", groupMemberMock, groupMemberMock);
    }

    @Test
    public void deleteRating() {

    }

    @Test
    public void getById() {
        //Give value that will be returned when a user is searched in the repo (id)
        when(ratingRepository.findById(0)).thenReturn(rating);
        //Get a user from the service
        Rating result = ratingServiceImpl.getById(0);
        //Check if the values are correct.
        assertEquals(0,result.getId());
        assertEquals(6.0, result.getGrade());
        assertEquals("TESTING", result.getComment());
        assertEquals(groupMemberMock, result.getGroupMember());
        assertEquals(groupMemberMock, result.getRatedMember());
    }

    @Test
    public void getByGroupMember() {
    }

    @Test
    public void getByRatedMember() {
    }

    @Test
    public void saveRating() {
    }
}