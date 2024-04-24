package com.example.sms.jobs;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;

/**
 * @author Santosh Kumar
 * @Created 20-04-2024
 */
public class readTradeFile {

    @Scheduled(cron = "0/10 * * ? * *")
    public void runEveryTwoMinutes(){
        System.out.println("File"+ System.currentTimeMillis());
    }


    @Scheduled(fixedDelay = 10000)
    public void run() {
        System.out.println("==============FixedDelay Task=============" + LocalTime.now());
    }
}
