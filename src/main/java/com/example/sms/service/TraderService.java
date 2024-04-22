package com.example.sms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    public List<TradeData> getAllTradeDataInLastTenMinutes()
    {

        System.out.println("-----------------File 1"+ System.currentTimeMillis());
        List<TradeData> tradeData = new ArrayList<TradeData>();
        //tradeRepository.getTradeRecord(10L).forEach(trade -> tradeData.add(trade));
        List<TradeData> tradeDataList = tradeRepository.getTradeRecord(10L);

        return tradeDataList;

//        return TradeData.builder()
//                .Amount(tradeRepository.getTradeRecord(10L).getAmount())
//                .unique_Trader_id(tradeRepository.getTradeRecord(10L).getUnique_Trader_id()).build();
//

//        System.out.println("---------------Unique_Trader_id---------------"+ tradeData1.getUnique_Trader_id());
//
//        return tradeData;
    }



    public List<String> getLastTemMinRecords(){
        List<String> tradeDataList= tradeRepository.getTemMinutesRecord(10);
        return tradeDataList;
    }

    public TradeData getFraudTradersDetails(String uniqueTradeId){
        TradeData tradeData = tradeRepository.getFraudTradersDetails(uniqueTradeId);
        return tradeData;
    }


    public void UpdateFraudTraderFlag(String uniqueTradeId){
         tradeRepository.updateTraderAsFraud(uniqueTradeId);
    }





}
