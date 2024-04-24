package com.example.sms.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */

@AllArgsConstructor
@Getter
public enum SMSErrorCode {


    STOCK_MANIPULATION_ERROR("","INTERNAL ERROR IN STOCK MANIPULATION SYSTEM",HttpStatus.INTERNAL_SERVER_ERROR);

    private String messageCode;
    private String message;
    private HttpStatus httpStatusCode;


}
