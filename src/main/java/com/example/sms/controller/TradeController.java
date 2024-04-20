package com.example.sms.controller;

import java.io.File;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.sms.entity.TradeData;
import com.example.sms.jms.Trader;
import com.example.sms.repository.TradeRepository;
import com.example.sms.service.TraderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import jakarta.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//creating RestController
@RestController
public class TradeController
{
    //autowired the StudentService class
    @Autowired
    TraderService traderService;

    @Autowired
    TradeRepository tradeRepository;
    //creating a get mapping that retrieves all the students detail from the database


    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @RequestMapping("/")
    public String home(){
        return "Stock Manupulation System";
    }


    @GetMapping("/trader")
    private List<TradeData> getAll()
    {
        return traderService.getAllTrade();
    }
    //creating a get mapping that retrieves the detail of a specific student
    @GetMapping("/trader/{traderId}")
    private TradeData getStudent(@PathVariable("traderId") int id)
    {
        return traderService.getTradeById(id);
    }
    //creating a delete mapping that deletes a specific student
    @DeleteMapping("/trader/{id}")
    private void deleteStudent(@PathVariable("id") int id)
    {
        traderService.delete(id);
    }
    //creating post mapping that post the student detail in the database
    @PostMapping("/trader")
    private void saveTrader(@RequestBody TradeData tradeData)
    {
        traderService.saveOrUpdate(tradeData);
        System.out.println("Trade Data"+ tradeData.toString());
        //return tradeData.getId();
    }

    // Call REST Web Services of Regulatory authories of two countries

    @PostMapping("/regulatoryAuthorities/country/india")
    private void regulatoryAuthoritiesIndia(@RequestBody Trader trader)
    {
        sendMessage(trader);
        System.out.println("Trade Data"+ trader.toString());

    }
    @PostMapping("/regulatoryAuthorities/country/japan")
    private void regulatoryAuthoritiesJapan(@RequestBody Trader trader)
    {
        sendMessage(trader);
        System.out.println("Trade Data"+ trader.toString());

    }

    public Trader sendMessage(@RequestBody Trader trader) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String studentAsJson = mapper.writeValueAsString(trader);

            jmsTemplate.convertAndSend(queue, studentAsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trader;
    }




}
