package com.example.sms.exceptions;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */
public class StockManupulationDataException extends RuntimeException{

    public StockManupulationDataException() {
    }

    public StockManupulationDataException(String message) {
        super(message);
    }
}
