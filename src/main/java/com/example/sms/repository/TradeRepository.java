package com.example.sms.repository;

import com.example.sms.entity.TradeData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface TradeRepository extends CrudRepository<TradeData, Integer>
{

//    @Query(value = "SELECT ID id , buy_or_sell bos, currency cur,trade_time tr,amount amt,FIRST_NAME FN,LAST_NAME LN,NATIONALITY N,COUNTRY_OF_RESIDENCE COR,DATE_OF_BIRTH DOB,UNIQUE_TRADER_ID TR_ID,UNIQUE_STOCK_ID US_ID\n" +
//            "FROM TRADE_DATA\n" +
//            "where TRADE_TIME >  CURRENT_TIMESTAMP - INTERVAL '10' MINUTE\n" +
//            "GROUP BY UNIQUE_STOCK_ID, UNIQUE_TRADER_ID , ID \n" +
//            "Having count(BUY_OR_SELL) = 1", nativeQuery = true)
//    List<TradeData> getTradeRecord(Long timeInMinutes);

//    @Query(value = "SELECT * from TradeData td WHERE td.trade_time >= (now() - interval 5 minute ", nativeQuery = true)
//    TradeData getTenMinTradeRecord(Long timeInMinutes);
        @Query(value = "SELECT FIRST_NAME,LAST_NAME,NATIONALITY ,COUNTRY_OF_RESIDENCE,DATE_OF_BIRTH,UNIQUE_TRADER_ID ,UNIQUE_STOCK_ID\n" +
        "FROM TRADE_DATA where UNIQUE_TRADER_ID in (\n" +
        "SELECT count(*),UNIQUE_TRADER_ID ,UNIQUE_STOCK_ID \n" +
        "FROM TRADE_DATA \n" +
        "where TRADE_TIME >  CURRENT_TIMESTAMP - INTERVAL '10' MINUTE\n" +
        "GROUP BY BUY_OR_SELL,UNIQUE_STOCK_ID, UNIQUE_TRADER_ID\n" +
        "Having count(BUY_OR_SELL) > 5 )", nativeQuery = true)
        List<TradeData> getTradeRecord(Long timeInMinutes);

        @Query(value="SELECT UNIQUE_TRADER_ID \n" +
                "FROM TRADE_DATA \n" +
                "where TRADE_TIME >  CURRENT_TIMESTAMP - INTERVAL '10' MINUTE\n" +
                "GROUP BY BUY_OR_SELL,UNIQUE_STOCK_ID, UNIQUE_TRADER_ID\n" +
                "Having count(BUY_OR_SELL) > 1 ", nativeQuery = true)
        List<String> getTemMinutesRecord(int timeInMinutes);





        @Modifying
        @Query(value = "UPDATE TRADE_DATA set Fraud_Flag='YES' where UNIQUE_TRADER_ID ='ZE001'", nativeQuery = true)
        void updateTraderAsFraud(String UNIQUE_TRADER_ID);


        @Query(value = "SELECT * from TRADE_DATA where UNIQUE_TRADER_ID ='ZE001' limit 1", nativeQuery = true)
        TradeData getFraudTradersDetails(String UNIQUE_TRADER_ID);




}
