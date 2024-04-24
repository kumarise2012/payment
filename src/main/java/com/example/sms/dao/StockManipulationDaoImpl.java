package com.example.sms.dao;

import com.example.sms.entity.TradeData;
import com.example.sms.exceptions.StockManupulationDataException;
import com.example.sms.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class StockManipulationDaoImpl implements StockManipulationDao {

    private final TradeRepository tradeRepository;

    @Override
    public List<String> getTenMinData() {
        log.info("TraderService - GetLastTenMinutesRecords - Fetching Records for last 10 minutes");
        List<String> tradeDataList = null;
        try {
            tradeDataList = tradeRepository.getTemMinutesRecord(10);
        } catch(StockManupulationDataException exm){
            log.error("TraderService - GetLastTenMinutesRecords() - Exception Occupied while fetching the details from database");
            exm.getMessage();
        }
        return tradeDataList;
    }

    @Override
    public TradeData getSingleRecord(String uniqueTraderId) {
        log.info("TraderService - getFraudTradersDetails() - Fetching Fraud Trader Details");
        TradeData tradeData = null;
        try{
            tradeData =   tradeRepository.getFraudTradersDetails(uniqueTraderId);
        }catch(StockManupulationDataException ex){
            log.error("TraderService - getFraudTradersDetails() - Exception Occupied while fetching Fraud Trader details");
        }
        return tradeData;
    }

    @Override
    public void updateFraudTrader(String uniqueTraderId) {
        log.info("TraderService - UpdateFraudTraderFlag() - Fetching Fraud Trader Details - Started");
        try{
            tradeRepository.updateTraderAsFraud(uniqueTraderId);
        }catch(StockManupulationDataException ex){
            log.error("TraderService - UpdateFraudTraderFlag() - Exception Occupied while updating Flag");
        }
        log.info("TraderService - UpdateFraudTraderFlag() - Fetching Fraud Trader Details - Completed");

    }
}
