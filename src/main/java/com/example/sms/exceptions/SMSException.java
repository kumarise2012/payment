package com.example.sms.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * @author Santosh Kumar
 * @Created 24-04-2024
 */

@ControllerAdvice
@Slf4j
public class SMSException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= {APIException.class})
    protected ResponseEntity<Object> handleRuntimeException(final RuntimeException ex , final WebRequest request ,HttpStatus status){
        HttpStatusCode httpStatusCode = getErrorResponse();
        return handleRuntimeException(ex,request,status);

    }



    private  HttpStatusCode getErrorResponse(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }


}
