package com.example.sms;

import com.example.sms.jobs.FileProcessJob;
import org.mockito.InjectMocks;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Santosh Kumar
 * @Created 25-04-2024
 */
public class FileProcessJobTest {

    @InjectMocks
    FileProcessJob fileProcessJob;

    public void uploadFileDataTest() throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:TradersData.csv");




    }
}
