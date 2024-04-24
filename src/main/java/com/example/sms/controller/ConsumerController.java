package com.example.sms.controller;

import com.example.sms.jms.Consumer;
import com.example.sms.jms.Trader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Santosh Kumar
 * @Created 20-04-2024
 */

@Slf4j
@RestController
@RequestMapping("/consume")
public class ConsumerController {

    @Autowired
    private Consumer consumer;

    @GetMapping("/message")
    public ResponseEntity<Trader> getMqMessage(){
        log.info("ConsumerController :: getMqMessage() - Reading Message from ActiveMq");
        Trader mqData =  consumer.consumeMessage();
        return new ResponseEntity<>(mqData,HttpStatus.OK);
    }
}
