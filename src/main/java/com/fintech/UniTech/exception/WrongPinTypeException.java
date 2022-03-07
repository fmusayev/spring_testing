package com.fintech.UniTech.exception;

public class WrongPinTypeException extends RuntimeException{

    public WrongPinTypeException(String message){
        super(message);
    }
}
