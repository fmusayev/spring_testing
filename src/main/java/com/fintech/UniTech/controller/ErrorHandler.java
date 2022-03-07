package com.fintech.UniTech.controller;

import com.fintech.UniTech.dto.ErrorResponse;
import com.fintech.UniTech.exception.DeActiveAccountException;
import com.fintech.UniTech.exception.InsufficientBalanceException;
import com.fintech.UniTech.exception.NonExistAccountException;
import com.fintech.UniTech.exception.SameAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SameAccountException.class)
    public ErrorResponse handleException(SameAccountException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InsufficientBalanceException.class)
    public ErrorResponse handleException(InsufficientBalanceException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DeActiveAccountException.class)
    public ErrorResponse handleException(DeActiveAccountException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NonExistAccountException.class)
    public ErrorResponse handleException(NonExistAccountException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
