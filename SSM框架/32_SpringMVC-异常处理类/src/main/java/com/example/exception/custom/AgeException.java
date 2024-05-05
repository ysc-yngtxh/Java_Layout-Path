package com.example.exception.custom;

import com.example.exception.MyUserException;

/**
 * @author 游家纨绔
 */
public class AgeException extends MyUserException {
    public AgeException() {
        super();
    }

    public AgeException(String message) {
        super(message);
    }
}
