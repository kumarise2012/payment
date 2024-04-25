package com.example.sms.service;

import java.util.List;

import com.example.sms.dao.StockManipulationDao;
import com.example.sms.entity.TradeData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author Santosh Kumar
 * @Created 20-04-2024
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StockManipulationServiceImpl implements StockManupulationService {
    private final StockManipulationDao stockManipulationDao;

    public List<String> getLastTemMinRecords(int minute) {
        return stockManipulationDao.getTenMinData(minute);
    }

    public TradeData getFraudTradersDetails(String uniqueTradeId) {
        return stockManipulationDao.getSingleRecord(uniqueTradeId);
    }

    public void UpdateFraudTraderFlag(String uniqueTradeId) {
        stockManipulationDao.updateFraudTrader(uniqueTradeId);
    }

}