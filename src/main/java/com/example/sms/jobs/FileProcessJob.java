package com.example.sms.jobs;

import com.example.sms.entity.TradeData;
import com.example.sms.exceptions.RegulatoryAuthoritiesException;
import com.example.sms.jms.Trader;
import com.example.sms.repository.TradeRepository;
import com.example.sms.service.StockManipulationServiceImpl;
import com.example.sms.constants.SMSConstants;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author Santosh Kumar
 * @Created 21-04-2024
 */

@Slf4j
@Component
public class FileProcessJob {
    @Autowired
    StockManipulationServiceImpl stockManipulationServiceImpl;

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    ResourceLoader resourceLoader;

//    @Value("${regulatory.authorities.uri}")
//    private String regulatoryAuthoritiesUri;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sms.file.reader.cron}")
    private String fileReaderCron;

    @Autowired
    private WebClient webClient;

    @Value("${address-service.base.url}")
    private String addressBaseUrl;

    @Scheduled(cron = SMSConstants.FILE_READER_CRON_EXP )
    public void runEveryTwoMinutes() throws Exception {
        try {
            log.info("FileProcessJob : runEveryTwoMinutes() - Cron job for file processing have been started");
            uploadFileData();
            runFraudTrader();
            log.info("FileProcessJob : runEveryTwoMinutes() - Cron job for file processing have been completed");
        }catch(Exception e){
            log.info("FileProcessJob : runEveryTwoMinutes() - Exception Occurred While Reading file"+ e.getMessage());
        }
    }

    public String uploadFileData() throws Exception{
        try {
            log.info("FileProcessJob : runEveryTwoMinutes() : uploadFileData - Uploading file data have started");
            File file = ResourceUtils.getFile("classpath:TradersData.csv");
            //Resource resource = resourceLoader.getResource("classpath:android.png");
            //InputStream input = resource.getInputStream();
            //File file = resource.getFile();
            //InputStream inputStream = file.getInputStream();
            CsvParserSettings csvParserSettings = new CsvParserSettings();
            csvParserSettings.setHeaderExtractionEnabled(true);
            csvParserSettings.setDelimiterDetectionEnabled(true, ';');
            CsvParser csvParser = new CsvParser(csvParserSettings);
            List<Record> parseAllRecords = csvParser.parseAllRecords(file);
            tradeRepository.saveAll(traderDataRecordMapping(parseAllRecords));
            log.info("FileProcessJob : runEveryTwoMinutes() : uploadFileData - Uploading file data have been completed");
        } catch (IOException e){
            log.error("FileProcessJob : runEveryTwoMinutes() : uploadFileData - Exception Occurred while reading data from file");
        }
        return "File Uploaded";
    }

    //RUn below job to all time which will take last 10 minstrsn
    //@Scheduled(cron = "0 0/1 * ? * *")
    public void runFraudTrader(){
        try {
            log.info("FileProcessJob : runEveryTwoMinutes() - runFraudTrader() checking for fraud Trader details in last 10 minutes - started");
            List<String> tradeDataListOfLastTenMinutes = stockManipulationServiceImpl.getLastTemMinRecords();
            List<Trader> traderList2 = new ArrayList<>();
            for (String t : tradeDataListOfLastTenMinutes) {
                String[] fraudTraderDetails = t.split(",");
                stockManipulationServiceImpl.UpdateFraudTraderFlag(fraudTraderDetails[0]);
                Trader traderDataMapper = traderDataMapper(fraudTraderDetails);
                TradeData t1 = stockManipulationServiceImpl.getFraudTradersDetails(t);
                traderList2.add(traderDataMapper);
            }
            //regulatoryAuthorities(SMSConstants.REGULATORY_AUTHORITIES_API, traderList2);
            regulatoryAuthoritiesWebClient(traderList2,addressBaseUrl);
            log.info("FileProcessJob : runEveryTwoMinutes() - runFraudTrader() checking for fraud Trader details in last 10 minutes - completed");
        }catch(Exception e){
            log.error("FileProcessJob : runEveryTwoMinutes() - runFraudTrader() - Exception occurred while fetching details for fraud trader from H2 - database");
        }
    }

    public void regulatoryAuthorities(String uri , List<Trader> fraudTraderList){
        log.info("FileProcessJob : regulatoryAuthorities()- Started");
        if(!fraudTraderList.isEmpty()) {
            try {
                restTemplate.postForObject(uri, fraudTraderList, ResponseEntity.class);
            } catch (RegulatoryAuthoritiesException rae) {
                log.error("FileProcessJob : regulatoryAuthorities()- Exception while invoking Regulatory Authorities");
            }
        }else{
            log.info("FileProcessJob : regulatoryAuthorities()- No Fraud Traders Found");
        }
        log.info("FileProcessJob : regulatoryAuthorities()- Completed");
    }

    public void regulatoryAuthoritiesWebClient(List<Trader> fraudTraderList,String baseUrl){
        log.info("FileProcessJob : regulatoryAuthorities() - regulatoryAuthoritiesWebClient");
        WebClient client = WebClient.create(baseUrl);
        Mono<TradeData> mono= client
                .post()
                .uri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(fraudTraderList),new ParameterizedTypeReference<List<TradeData>>() {})
                .retrieve().bodyToMono(TradeData.class)
                .retryWhen(Retry.backoff(5, Duration.ofSeconds(5))
                        .filter(throwable -> throwable instanceof HttpServerErrorException ||
                                throwable instanceof TimeoutException).doAfterRetry( client1 -> {
                                    log.info("Message after 5 attempts of retry - Regulatory Authorities Service is down ");}));
        mono.subscribe(System.out::println);
        log.info("FileProcessJob : regulatoryAuthorities() - regulatoryAuthoritiesWebClient - completed");
    }


    public Trader traderDataMapper(String [] traderData){
        log.info("FileProcessJob : runEveryTwoMinutes() : uploadFileData() : traderDataMapper - mapping each db record with Producer Object");
        Trader traderDetails = null;
       for(String t : traderData){
          traderDetails =  new Trader();
          traderDetails.setTraderId(traderData[0]);
           traderDetails.setStockId(traderData[1]);
           traderDetails.setFirstName(traderData[2]);
           traderDetails.setLastName(traderData[3]);
           traderDetails.setNationality(traderData[4]);
           traderDetails.setCountryOfResidence(traderData[5]);
           traderDetails.setDate_of_birth(traderData[6]);
           traderDetails.setFraudDetectionTime(new Time(System.currentTimeMillis()).toString());

           log.info("FileProcessJob : runEveryTwoMinutes()============"+ traderDetails.getTraderId() + traderDetails.getFirstName());
       }
        return traderDetails;
    }

    public List<TradeData> traderDataRecordMapping(List<Record> recordList){
        log.info("FileProcessJob - traderDataRecordMapping has been Started ");
        List<TradeData> tradeDataList = new ArrayList<>();
        recordList.forEach(record -> {
            TradeData tradeData = new TradeData();
            tradeData.setFirst_name(record.getString("First_name"));
            tradeData.setLast_Name(record.getString("Last_Name"));
            tradeData.setNationality(record.getString("Nationality"));
            tradeData.setCountry_of_Residence(record.getString("Country_of_Residence"));
            tradeData.setDate_of_birth(record.getString("date_of_birth"));
            tradeData.setUnique_Trader_id(record.getString("Unique_Trader_id"));
            tradeData.setAmount(record.getString("Amount"));
            tradeData.setCurrency(record.getString("currency"));
            tradeData.setUnique_Stock_ID(record.getString("unique_Stock_ID"));
            tradeData.setBuy_or_Sell(record.getString("Buy_or_Sell"));
            tradeData.setTradeTime(ZonedDateTime.now());
            tradeDataList.add(tradeData);
        });
        log.info("FileProcessJob - traderDataRecordMapping has been - Completed ");
        return tradeDataList;
    }
}
