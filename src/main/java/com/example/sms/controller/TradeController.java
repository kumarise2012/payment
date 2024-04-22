package com.example.sms.controller;

import java.io.File;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.sms.entity.TradeData;
import com.example.sms.jms.Consumer;
import com.example.sms.jms.Producer;
import com.example.sms.jms.Trader;
import com.example.sms.repository.TradeRepository;
import com.example.sms.service.TraderService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import jakarta.jms.Queue;
import org.apache.tomcat.util.json.JSONParser;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//creating RestController
@RestController
public class TradeController
{

    @Autowired
    TraderService traderService;

    @Autowired
    TradeRepository tradeRepository;


    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Producer producer;

    @RequestMapping("/")
    public String home(){
        return "Stock Manupulation System";
    }


    @GetMapping("/trader")
    private List<TradeData> getAll()
    {
        return traderService.getAllTrade();
    }

    @GetMapping("/trader/{traderId}")
    private TradeData getStudent(@PathVariable("traderId") int id)
    {
        return traderService.getTradeById(id);
    }

    @DeleteMapping("/trader/{id}")
    private void deleteStudent(@PathVariable("id") int id)
    {
        traderService.delete(id);
    }

    @PostMapping("/trader")
    private void saveTrader(@RequestBody TradeData tradeData)
    {
        traderService.saveOrUpdate(tradeData);
        System.out.println("Trade Data"+ tradeData.toString());
        //return tradeData.getId();
    }

    // Call REST Web Services of Regulatory authories of two countries

    @PostMapping("/regulatoryAuthorities/country/india")
    private void regulatoryAuthoritiesIndia(@RequestBody List<Trader> trader)
    {
        producer.sendMQMessage(trader);
        System.out.println("Trade Data"+ trader.toString());

    }
    @PostMapping("/regulatoryAuthorities/country/japan")
    private void regulatoryAuthoritiesJapan(@RequestBody List<Trader> trader)
    {
        producer.sendMQMessage(trader);
        System.out.println("Trade Data"+ trader.toString());

    }





}
