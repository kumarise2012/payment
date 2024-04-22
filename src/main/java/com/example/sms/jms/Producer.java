package com.example.sms.jms;


import com.google.gson.Gson;
import jakarta.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


@RestController
@RequestMapping("/produce")
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @PostMapping("/message")
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

    public List<Trader> sendMQMessage(@RequestBody List<Trader> trader) {

        try {
            ObjectMapper mapper = new ObjectMapper();



            Gson gson = new Gson();
            // convert your list to json
            String jsonCartList = gson.toJson(trader);

//            JSONParser parser = new JSONParser(jsonCartList);
//            JSONObject json = (JSONObject) parser.parse();
//
//            System.out.println("Stting to json ==================="+ json);


            System.out.println("Setting to json ==================="+ jsonCartList.toString());

            String myObjects = mapper.writeValueAsString(jsonCartList);

            jmsTemplate.convertAndSend(queue, myObjects);


            System.out.println("===========Inside Sent method of Active MQ=============");




//            String studentAsJson = mapper.writeValueAsString(trader);
//            jmsTemplate.convertAndSend(queue, studentAsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trader;
    }

}