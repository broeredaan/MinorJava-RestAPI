package com.ajp.yourgrade.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.only;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApproveServiceImplTest {

    @Mock
    private ApproveServiceImpl approveServiceImplMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    //This method checks if the function approve is called the appropriate amount of times.
    @Test
    public void approve() {
        //Calls the method.
        approveServiceImplMock.approve(0);
        //Checks how many times the method has been called.
        verify(approveServiceImplMock, only()).approve(0);
    }

    //This method checks if the function setFinalGrade is called the appropriate amount of times.
    @Test
    public void setFinalGrade() {
        //Calls the method.
        approveServiceImplMock.setFinalGrade(0);
        //Checks how many times the method has been called.
        verify(approveServiceImplMock, only()).setFinalGrade(0);
    }

    //This method checks if the function createPdf is called the appropriate amount of times.
    @Test
    public void createPdf() {
        //In order to test this function the Exception that it can give has to be catched or thrown.
        try{
            //Calls the method.
            approveServiceImplMock.createPdf(0);
            //Checks how many times the method has been called.
            verify(approveServiceImplMock, only()).createPdf(0);
        }catch(IOException e){
            //Catch the exception if it occurs
            e.printStackTrace();
        }
    }
}