package com.example.sms.jms;

import java.io.Serializable;

public class Trader implements Serializable {
    private static final long serialVersionUID = 1L;
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
}
