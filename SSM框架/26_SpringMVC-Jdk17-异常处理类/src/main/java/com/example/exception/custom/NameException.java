package com.example.exception.custom;

import com.example.exception.MyUserException;

/**
 * @author 游家纨绔
 */
public class NameException extends MyUserException {

    public NameException() {
        super();
    }

    public NameException(String message) {
        super(message);
    }
}
