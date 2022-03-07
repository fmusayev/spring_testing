package com.fintech.UniTech.exception;

public class NonExistAccountException extends RuntimeException{

    public NonExistAccountException(String message){
        super(message);
    }
}
