package com.fintech.UniTech.exception;

public class DeActiveAccountException extends RuntimeException{

    public DeActiveAccountException(String message){
        super(message);
    }
}
