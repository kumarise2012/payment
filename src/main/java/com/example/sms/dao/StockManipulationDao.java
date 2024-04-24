package com.example.sms.dao;

import com.example.sms.entity.TradeData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */

@Service
public interface StockManipulationDao {
    List<String> getTenMinData();
    TradeData getSingleRecord(String uniqueTraderId);
    void updateFraudTrader(String uniqueTraderId);


}
