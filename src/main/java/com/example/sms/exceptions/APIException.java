package com.example.sms.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */
public class APIException extends RuntimeException{


    public APIException(){

    }
    public APIException(final String localizesMessage){
        super(localizesMessage);
    }

}
