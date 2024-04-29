package com.example.sms.controller;


import java.util.List;

import com.example.sms.jms.Producer;
import com.example.sms.jms.Trader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Santosh Kumar
 * @Created 20-04-2024
 */

@Slf4j
@RestController
@RequestMapping("/regulatoryAuthorities")
public class RegulatoryAuthoritiesController {

    @Autowired
    private Producer producer;

    @PostMapping("/country/japan")
    private void regulatoryAuthoritiesJapan(@RequestBody List<Trader> trader) {
        try {
            producer.sendMQMessage(trader);
            log.info("Regulatory Authorities of Japan  has been informed");
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    @PostMapping("/country/india")
    private void regulatoryAuthoritiesIndia(@RequestBody List<Trader> trader) {
        try {
            producer.sendMQMessage(trader);
            log.info("Regulatory Authorities of India  has been informed");
        } catch (Exception exception){
            log.error(exception.getMessage());
        }

    }
}
