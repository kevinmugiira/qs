package com.example.qs.exceptions;

public class CustomException extends IllegalArgumentException{

    public CustomException(String msg) {
        super(msg);
    }
}
