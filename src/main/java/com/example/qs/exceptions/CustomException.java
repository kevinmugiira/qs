package com.example.qs.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomException extends IllegalArgumentException{

    public CustomException(String msg) {
        super(msg);
    }
}
