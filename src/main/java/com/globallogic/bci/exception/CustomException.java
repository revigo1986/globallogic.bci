package com.globallogic.bci.exception;

public class CustomException extends RuntimeException {

    private final int code;

    public CustomException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}