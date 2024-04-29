package com.example.sms.service;

import com.example.sms.entity.TradeData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */

@Service
public interface StockManupulationService {

    List<String> getLastTemMinRecords(int minutes);

    TradeData getFraudTradersDetails(String uniqueTradeId);

    void UpdateFraudTraderFlag(String uniqueTradeId);

}
