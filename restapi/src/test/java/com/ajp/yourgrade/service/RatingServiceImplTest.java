package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Rating;
import com.ajp.yourgrade.persistence.RatingRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.only;

@RunWith(SpringJUnit4ClassRunner.class)
class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingServiceImpl;

    @Mock
    private RatingServiceImpl ratingServiceImplMock;

    @Mock
    private GroupMember GroupMemberMock;

    private Rating rating = new Rating(6,"TESTING",GroupMemberMock,GroupMemberMock);


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addRating() {
        ratingServiceImplMock.addRating(8, "Testing", GroupMemberMock, GroupMemberMock);
        verify(ratingServiceImplMock, only()).addRating(8, "Testing", GroupMemberMock, GroupMemberMock);
    }

    @Test
    void deleteRating() {

    }

    @Test
    void getById() {
        /*//Give value that will be returned when a user is searched in the repo (id)
        when(ratingRepository.findById(0)).thenReturn(rating);
        //Get a user from the service
        Rating result = templateServiceImpl.getTemplateById(0);
        //Check if the values are correct.
        assertEquals(0,result.getId());
        assertEquals("Test", result.getName());
        assertEquals(1, result.getGradeDeviation());
        assertEquals(true, result.isCommentNeeded());*/
    }

    @Test
    void getByGroupMember() {
    }

    @Test
    void getByRatedMember() {
    }

    @Test
    void saveRating() {
    }
}