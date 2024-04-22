package com.example.sms.jms;

import com.example.sms.entity.TradeData;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

@JsonDeserialize
public class Trader implements Serializable {
    private static final long serialVersionUID = 1L;

   // private List<TradeData> frautTradeDataList;
    private String traderId;
    private String stockId;
    private String buyOrSell;

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

//    public List<TradeData> getFrautTradeDataList() {
//        return frautTradeDataList;
//    }
//
//    public void setFrautTradeDataList(List<TradeData> frautTradeDataList) {
//        this.frautTradeDataList = frautTradeDataList;
//    }
}
