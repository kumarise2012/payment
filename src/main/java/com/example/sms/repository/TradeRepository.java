package com.example.sms.repository;

import com.example.sms.entity.TradeData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Santosh Kumar
 * @Created 21-04-2024
 */
@Transactional
@Repository
public interface TradeRepository extends CrudRepository<TradeData, Integer> {

    @Modifying
    @Query(value = "UPDATE TRADE_DATA td set td.Fraud_Flag='YES' where td.UNIQUE_TRADER_ID =:unique_Trader_id", nativeQuery = true)
    void updateTraderAsFraud(@Param("unique_Trader_id") String UNIQUE_TRADER_ID);

    @Query(value = "SELECT * from TRADE_DATA td where td.UNIQUE_TRADER_ID =:unique_Trader_id limit 1", nativeQuery = true)
    TradeData getFraudTradersDetails(@Param("unique_Trader_id") String UNIQUE_TRADER_ID);

    @Query(value = "Select distinct UNIQUE_TRADER_ID , UNIQUE_STOCK_ID , First_name , Last_Name ,Nationality , Country_of_Residence ,date_of_birth from TRADE_DATA  u\n" +
            "where TRADE_TIME >  CURRENT_TIMESTAMP - INTERVAL '10' MINUTE\n" +
            "GROUP BY UNIQUE_STOCK_ID, UNIQUE_TRADER_ID\n" +
            "Having count(BUY_OR_SELL) > 5 ", nativeQuery = true)
    List<String> getTemMinutesRecord(int timeInMinutes);

}
