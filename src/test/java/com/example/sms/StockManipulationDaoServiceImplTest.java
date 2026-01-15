package com.example.sms;

import com.example.sms.exceptions.StockManupulationDataException;
import com.example.sms.repository.TradeRepository;
import com.example.sms.service.StockManipulationServiceImpl;
import com.example.sms.service.StockManupulationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doThrow;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */

@RunWith(MockitoJUnitRunner.class)
public class StockManipulationDaoServiceImplTest {

    @Mock
    private StockManipulationServiceImpl subjectUnderTest;

    @Mock
    private TradeRepository tradeRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateFraudTraderFlag(){


        //Mockito.lenient().doNothing().when(tradeRepository).updateTraderAsFraud("12345");

        //doThrow(new RuntimeException()).when(subjectUnderTest).UpdateFraudTraderFlag("12345");

        //Mockito.lenient().doNothing().when(subjectUnderTest).UpdateFraudTraderFlag("12345");
        //Mockito.verify(subjectUnderTest, Mockito.times(1)).UpdateFraudTraderFlag("12345");
        //Mockito.when(subjectUnderTest.getFraudTradersDetails("12345")).thenThrow(new StockManupulationDataException());

        Mockito.doNothing().when(subjectUnderTest).UpdateFraudTraderFlag("12345");

        subjectUnderTest.UpdateFraudTraderFlag("12345");






    }



}
