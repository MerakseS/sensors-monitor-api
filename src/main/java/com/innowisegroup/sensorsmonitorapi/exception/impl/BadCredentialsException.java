package com.innowisegroup.sensorsmonitorapi.exception.impl;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException(String message) {
        super(message);
    }

    public BadCredentialsException(Throwable cause) {
        super(cause);
    }
}
