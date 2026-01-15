package com.example.sms.exceptions;

import lombok.Getter;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */
public class SMSInternalException extends RuntimeException{


    public SMSInternalException() {
    }

    public SMSInternalException(String message) {
        super(message);
    }
}
