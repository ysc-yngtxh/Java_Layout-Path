package com.example.exception;

import java.io.Serial;

public class ApiException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8985782379344330661L;
    public ApiException(String msg) {
        super(msg);
    }
}

