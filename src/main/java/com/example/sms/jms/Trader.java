package com.example.sms.jms;

import com.example.sms.entity.TradeData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.Time;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Santosh Kumar
 * @Created 20-04-2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@JsonIgnoreProperties
public class Trader implements Serializable {
    private static final long serialVersionUID = 1L;

    private String traderId;
    private String stockId;

    private String firstName;

    private String lastName;

    private String nationality;

    private String countryOfResidence;

    private String date_of_birth;
    //private String buyOrSell;

    private String fraudDetectionTime;


//    @JsonCreator
//    public static Trader of(@JsonProperty("traderId") String traderId , @JsonProperty("stockId") String stockId,
//                            @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
//                            @JsonProperty("nationality") String nationality , @JsonProperty("countryOfResidence") String countryOfResidence ,
//                            @JsonProperty("date_of_birth") String date_of_birth , @JsonProperty("fraudDetectionTime") String fraudDetectionTime) {
//
//
//        Trader trader = new Trader();
//
//        trader.traderId = traderId;
//        trader.stockId = stockId;
//        trader.nationality = nationality;
//
//        trader.countryOfResidence = countryOfResidence;
//        trader.date_of_birth = date_of_birth;
//        trader.fraudDetectionTime = Time.valueOf(fraudDetectionTime);
//        trader.firstName = firstName;
//        trader.lastName = lastName;


//        return trader;
//    }

    public String getFraudDetectionTime() {
        return fraudDetectionTime;
    }

    public void setFraudDetectionTime(String fraudDetectionTime) {
        this.fraudDetectionTime = fraudDetectionTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

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

    @Override
    public String toString() {
        return "Trader{" +
                "traderId='" + traderId + '\'' +
                ", stockId='" + stockId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", countryOfResidence='" + countryOfResidence + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", fraudDetectionTime=" + fraudDetectionTime +
                '}';
    }
}
