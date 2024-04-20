DROP TABLE IF EXISTS CITY;
--CREATE TABLE CITY (
--City_code INT AUTO_INCREMENT  PRIMARY KEY,
--city_name VARCHAR(50) NOT NULL,
--city_pin INT NOT NULL,
--city_namea VARCHAR(50) NOT NULL
--);


CREATE TABLE TradeData (
id INTEGER AUTO_INCREMENT  PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_Name VARCHAR(50) NOT NULL,
nationality VARCHAR(2) NOT NULL,
country_of_Residence VARCHAR(2) NOT NULL,
date_of_birth VARCHAR(20) NOT NULL,
unique_Trader_id VARCHAR(10) NOT NULL,
amount DOUBLE NOT NULL,
currency VARCHAR(3) NOT NULL,
unique_Stock_ID VARCHAR(10) NOT NULL,
buy_or_Sell VARCHAR(3) NOT NULL,
trade_time TIMESTAMP
);