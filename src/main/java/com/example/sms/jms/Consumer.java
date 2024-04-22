package com.example.sms.jms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    public List<Trader> consumeMessage() {

        System.out.println("Message Listener Start---------------- ");

        List<Trader> trader = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonMessage = (String) jmsTemplate.receiveAndConvert(queue);
            //trader = mapper.readValue(jsonMessage, Trader.class);





            System.out.println("Message Listener Start---------------- "+ jsonMessage.toString());

            Gson gson = new Gson();



            Type listType = new TypeToken<ArrayList<Trader>>(){}.getType();
            List<Trader> yourClassList = new Gson().fromJson(jsonMessage, listType);

            System.out.println("Message Listener Start---------------- "+ yourClassList);




            // convert your list to json
            //String jsonCartList = gson.fromJson(jsonMessage);



            System.out.println("Message Listener---------------- "+ trader.toString());



           // System.out.println("Message Listener---------------- "+ objects.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return trader;
    }
}

