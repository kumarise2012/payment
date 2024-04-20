package com.example.sms.jobs;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;

public class readTradeFile {

    @Scheduled(cron = "0/10 * * ? * *")
    public void runEveryTwoMinutes(){
        System.out.println("File"+ System.currentTimeMillis());
    }


    @Scheduled(fixedDelay = 10000)
    public void run() {
        System.out.println("Current time is :: " + LocalTime.now());
    }
}
