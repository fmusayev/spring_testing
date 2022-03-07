package com.fintech.UniTech.exception;

public class WrongPasswordTypeException extends RuntimeException{

    public WrongPasswordTypeException(String message){
        super(message);
    }
}
