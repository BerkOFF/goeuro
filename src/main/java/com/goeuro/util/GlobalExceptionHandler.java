package com.goeuro.util;

import com.goeuro.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ErrorResponse handle(IllegalArgumentException exc) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ServletRequestBindingException.class)
    public ErrorResponse handle(ServletRequestBindingException exc) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handle(Exception exc) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exc.getMessage());
    }
}
