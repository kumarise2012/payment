package com.example.sms.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Component
//public class Consumer {
//
//    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
//
//    @JmsListener(destination = "javatute-queue")
//    public void consumeMessage(String message) {
//        logger.info("Message received from activemq queue---"+message);
//    }
//}



@RestController
@RequestMapping("/consume")
public class Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @GetMapping("/message")
    public Trader consumeMessage() {

        Trader trader = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonMessage = (String) jmsTemplate.receiveAndConvert(queue);
            trader = mapper.readValue(jsonMessage, Trader.class);
            System.out.println("Message Listener---------------- "+ trader.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return trader;
    }
}

