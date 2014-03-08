package com.shekhar.confsays.exceptions;

public class ConferenceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ConferenceNotFoundException(String message) {
        super(message);
    }
}
