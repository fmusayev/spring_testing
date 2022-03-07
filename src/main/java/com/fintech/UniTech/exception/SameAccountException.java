package com.fintech.UniTech.exception;

public class SameAccountException extends RuntimeException{

    public SameAccountException(String message){
        super(message);
    }
}
