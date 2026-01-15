package com.example.sms.exceptions;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */
public class ActiveMqException extends RuntimeException{
    public ActiveMqException() {
    }

    public ActiveMqException(String message) {
        super(message);
    }
}
