package com.fintech.UniTech.exception;

public class PinAlreadyExistException extends RuntimeException {

    public PinAlreadyExistException(String message) {
        super(message);
    }
}
