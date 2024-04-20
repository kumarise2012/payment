package com.example.sms.repository;

import com.example.sms.entity.TradeData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TradeRepository extends CrudRepository<TradeData, Integer>
{

    @Query(value = "SELECT * from TradeData td WHERE td.first_name = 'Jyoti' ", nativeQuery = true)
    TradeData getTradeRecord(Long timeInMinutes);


}
