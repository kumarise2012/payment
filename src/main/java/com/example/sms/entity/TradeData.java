package com.example.sms.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.ZonedDateTime;
/**
 * @author Santosh Kumar
 * @Created 20-04-2024
 */
@Data
@Entity
@Builder
@Table(name="TradeData")
@JsonPropertyOrder()
@NoArgsConstructor
@AllArgsConstructor
public class TradeData{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String unique_Trader_id;

    @Column
    private String First_name;

    @Column
    private String Last_Name;

    @Column
    private String Nationality;
    @Column
    private String Country_of_Residence;

    @Column
    private String date_of_birth;

    @Column
    private String Amount;

    @Column
    private String currency;

    @Column
    private String unique_Stock_ID;

    @Column
    private String Buy_or_Sell;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column
    private ZonedDateTime tradeTime;

    @Column
    private String fraudFlag;

//    @PrePersist
//    private void onCreate() {
//        tradeTime = new ZonedDateTime().no;
//    }

    public String getFraudFlag() {
        return fraudFlag;
    }

    public void setFraudFlag(String fraudFlag) {
        this.fraudFlag = fraudFlag;
    }

    public ZonedDateTime getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(ZonedDateTime tradeTime) {
        this.tradeTime = tradeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getCountry_of_Residence() {
        return Country_of_Residence;
    }

    public void setCountry_of_Residence(String country_of_Residence) {
        Country_of_Residence = country_of_Residence;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getUnique_Trader_id() {
        return unique_Trader_id;
    }

    public void setUnique_Trader_id(String unique_Trader_id) {
        this.unique_Trader_id = unique_Trader_id;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnique_Stock_ID() {
        return unique_Stock_ID;
    }

    public void setUnique_Stock_ID(String unique_Stock_ID) {
        this.unique_Stock_ID = unique_Stock_ID;
    }

    public String getBuy_or_Sell() {
        return Buy_or_Sell;
    }

    public void setBuy_or_Sell(String buy_or_Sell) {
        Buy_or_Sell = buy_or_Sell;
    }

    @Override
    public String toString() {
        return "TradeData{" +
                ", First_name='" + First_name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", Nationality='" + Nationality + '\'' +
                ", Country_of_Residence='" + Country_of_Residence + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", Unique_Trader_id=" + unique_Trader_id +
                ", Amount=" + Amount +
                ", currency='" + currency + '\'' +
                ", unique_Stock_ID='" + unique_Stock_ID + '\'' +
                ", Buy_or_Sell='" + Buy_or_Sell + '\'' +
                '}';
    }
}
