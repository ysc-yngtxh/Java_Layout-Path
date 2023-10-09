package com.example.exception;

import java.io.Serial;

public class APIException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8985782379344330661L;
    public APIException(String msg) {
        super(msg);
    }
}

