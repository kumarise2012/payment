package com.example.sms;

import com.example.sms.dao.StockManipulationDaoImpl;
import com.example.sms.entity.TradeData;
import com.example.sms.exceptions.StockManupulationDataException;
import com.example.sms.jms.Trader;
import com.example.sms.repository.TradeRepository;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StockManipulationDaoImplTest {

    @InjectMocks
    private StockManipulationDaoImpl subjectUnderTest;

    @Mock
    private TradeRepository tradeRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateFraudTraderTest(){
        Mockito.doNothing().when(tradeRepository).updateTraderAsFraud("12345");
        subjectUnderTest.updateFraudTrader("12345");

    }
    @Test
    public void getFraudTradersDetailsTest(){
        Mockito.doReturn(getTradeData()).when(tradeRepository).getFraudTradersDetails("12345");
        subjectUnderTest.getSingleRecord("12345");

    }

    @Test
    public void getTenMinDataTest(){
        Mockito.doReturn(getTradeDataList()).when(tradeRepository).getTemMinutesRecord(10);
        subjectUnderTest.getTenMinData(10);

    }


    public TradeData getTradeData(){
        TradeData trader = new TradeData();
        trader.setUnique_Stock_ID("123");
        trader.setUnique_Stock_ID("ME123");
        trader.setFirst_name("Santosh");
        trader.setLast_Name("kumar");
        trader.setCurrency("INR");
        trader.setDate_of_birth("11-01-1999");
        trader.setBuy_or_Sell("BUY");
        trader.setCountry_of_Residence("IN");
        trader.setAmount("50000");
        trader.setTradeTime(ZonedDateTime.now());
        trader.setFraudFlag("NO");
        return  trader;
    }

    public List<TradeData> getTradeDataList(){

        List<TradeData> tradeDataList = new ArrayList<>();
        TradeData trader = new TradeData();
        trader.setUnique_Stock_ID("123");
        trader.setUnique_Stock_ID("ME123");
        trader.setFirst_name("Santosh");
        trader.setLast_Name("kumar");
        trader.setCurrency("INR");
        trader.setDate_of_birth("11-01-1999");
        trader.setBuy_or_Sell("BUY");
        trader.setCountry_of_Residence("IN");
        trader.setAmount("50000");
        trader.setTradeTime(ZonedDateTime.now());
        trader.setFraudFlag("NO");

        tradeDataList.add(trader);
        return  tradeDataList;
    }
}
