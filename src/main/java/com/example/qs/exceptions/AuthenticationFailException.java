package com.example.qs.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {

    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
