package com.example.sms;

import com.example.sms.entity.TradeData;
import com.example.sms.jms.Trader;
import com.example.sms.repository.TradeRepository;
import com.example.sms.service.TraderService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.example.sms*")
public class SmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsApplication.class, args);
	}



	@Autowired
	TraderService traderService;

	@Autowired
	TradeRepository tradeRepository;

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	private RestTemplate restTemplate;

	//Read file on every 2 mins
	@Scheduled(cron = "0/10 * * ? * *")
	public void runEveryTwoMinutes() throws Exception {
		System.out.println("---File "+ System.currentTimeMillis());

		uploadFileData();
	}

	//RUn below job to all time which will take last 10 minstrsn
	@Scheduled(cron = "0/15 * * ? * *")
	public void runFraudTrader(){
		System.out.println("-----------------File 1"+ System.currentTimeMillis());
		TradeData td= traderService.getAllTradeDataInLastTenMinutes();

		System.out.print("==========getUnique_Trader_id()============"+td.getUnique_Trader_id()+"==========getUnique_Trader_id()=======");

		final String uri = "http://localhost:9091/regulatoryAuthorities/country/japan";

		Trader trader = new Trader();
		trader.setTraderId(td.getUnique_Trader_id());
		trader.setStockId("TATA");
		trader.setBuyOrSell("BUY");

		restTemplate.postForObject(uri,trader,Trader.class);

		System.out.print("========================Trader Data has been written on ACTIVE mQ======================");



	}


	public String uploadFileData() throws Exception{


		File file = ResourceUtils.getFile("classpath:config/TradersData.csv");


		//Resource resource = resourceLoader.getResource("classpath:android.png");
		//InputStream input = resource.getInputStream();

		//File file = resource.getFile();

		System.out.println("=================================="+file.getAbsoluteFile());


		List<TradeData> tradeDataList = new ArrayList<>();
		//InputStream inputStream = file.getInputStream();
		CsvParserSettings csvParserSettings = new CsvParserSettings();
		csvParserSettings.setDelimiterDetectionEnabled(true,';');

		CsvParser csvParser = new CsvParser(csvParserSettings);
		List<Record> parseAllRecords = csvParser.parseAllRecords(file);

		parseAllRecords.forEach( record -> {
			TradeData tradeData = new TradeData();
			tradeData.setFirst_name(record.getString("First_name"));
			tradeData.setLast_Name(record.getString("Last_Name"));
			tradeData.setNationality(record.getString("Nationality"));
			tradeData.setCountry_of_Residence(record.getString("Country_of_Residence"));
			tradeData.setDate_of_birth(record.getString("date_of_birth"));
			tradeData.setUnique_Trader_id(record.getString("Unique_Trader_id"));
			//tradeData.setAmount(record.getDouble("Amount"));
			tradeData.setCurrency(record.getString("currency"));
			tradeData.setUnique_Stock_ID(record.getString("unique_Stock_ID"));
			tradeData.setBuy_or_Sell(record.getString("Buy_or_Sell"));
			tradeData.setTradeTime(ZonedDateTime.now());
			tradeDataList.add(tradeData);
		});
		tradeRepository.saveAll(tradeDataList);
		return "File Uploaded";



	}

}
