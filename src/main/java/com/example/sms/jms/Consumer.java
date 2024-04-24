package com.example.sms.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.Queue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Component
@Slf4j
public class Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;


    public Trader consumeMessage() {
        Trader newTrader = null;
        try {
            log.info("Message -consumeMessage() -  Reading Message from Active MQ");
            ObjectMapper mapper = new ObjectMapper();
            String mqMessage = (String) jmsTemplate.receiveAndConvert(queue);
            newTrader = mapper.readValue(mqMessage, Trader.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newTrader;
    }
}

