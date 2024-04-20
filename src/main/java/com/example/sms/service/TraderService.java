package com.example.sms.service;

import java.util.ArrayList;
import java.util.List;

import com.example.sms.entity.TradeData;
import com.example.sms.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TraderService
{
    @Autowired
    TradeRepository tradeRepository;
    //getting all student records
    public List<TradeData> getAllTrade()
    {
        List<TradeData> tradeData = new ArrayList<TradeData>();
        tradeRepository.findAll().forEach(trade -> tradeData.add(trade));
        return tradeData;
    }
    //getting a specific record
    public TradeData getTradeById(int Unique_Trader_id)
    {
        return tradeRepository.findById(Unique_Trader_id).get();
    }
    public void saveOrUpdate(TradeData tradeData)
    {
        tradeRepository.save(tradeData);
    }
    //deleting a specific record
    public void delete(int uniqueTradeId)
    {
        tradeRepository.deleteById(uniqueTradeId);
    }


    public TradeData getAllTradeDataInLastTenMinutes()
    {

        System.out.println("-----------------File 1"+ System.currentTimeMillis());
        List<TradeData> tradeData = new ArrayList<TradeData>();
        //tradeRepository.getTradeRecord(10L).forEach(trade -> tradeData.add(trade));
        tradeRepository.getTradeRecord(10L);

        return TradeData.builder()
                .Amount(tradeRepository.getTradeRecord(10L).getAmount())
                .unique_Trader_id(tradeRepository.getTradeRecord(10L).getUnique_Trader_id()).build();


//        System.out.println("---------------Unique_Trader_id---------------"+ tradeData1.getUnique_Trader_id());
//
//        return tradeData;
    }
}
