package com.example.sms.jms;

import com.example.sms.exceptions.ActiveMqException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import jakarta.jms.Queue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author Santosh Kumar
 * @Created 20-04-2024
 */

@Slf4j
@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    public List<Trader> sendMQMessage(@RequestBody List<Trader> trader) {
        try {
            log.info("Producer :sendMQMessage - Producing Message on Active MQ Started ");
            for(Trader t : trader) {
                ObjectMapper mapper = new ObjectMapper();
                String studentAsJson = mapper.writeValueAsString(t);
                jmsTemplate.convertAndSend(queue, studentAsJson);
            }
            log.info("Producer :sendMQMessage - Producing Message on Active MQ Completed ");
        } catch (ActiveMqException mqException ) {
            log.error("=======Exception Occurred While Publishing Messaging on Active MQ============");
        } catch ( Exception e){
            e.printStackTrace();
        }
        return trader;
    }

}