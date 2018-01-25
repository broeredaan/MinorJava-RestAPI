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

    @Test
    public void approve() {
        approveServiceImplMock.approve(0);
        verify(approveServiceImplMock, only()).approve(0);
    }

    @Test
    public void setFinalGrade() {
        approveServiceImplMock.setFinalGrade(0);
        verify(approveServiceImplMock, only()).setFinalGrade(0);
    }

    @Test
    public void createPdf() {
        try{
            approveServiceImplMock.createPdf(0);
            verify(approveServiceImplMock, only()).createPdf(0);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}