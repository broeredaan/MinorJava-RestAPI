package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
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
public class PdfServiceImplTest {

    @Mock
    private PdfServiceImpl pdfServiceImplMock;

    @Mock
    private Group group;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    //This method checks if the function print is called the appropriate amount of times.
    @Test
    public void print() {
        //In order to test this function the Exception has to be catched or thrown.
        try{

            //Calls the method.
            pdfServiceImplMock.print(group);
            //Checks how many times the method has been called.
            verify(pdfServiceImplMock, only()).print(group);
        } catch (IOException e){
            //Catch the exception if it occurs
            e.getStackTrace();
        }
    }
}