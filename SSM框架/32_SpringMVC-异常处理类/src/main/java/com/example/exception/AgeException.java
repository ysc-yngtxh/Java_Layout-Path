package com.example.exception;

/**
 * @author 游家纨绔
 */
public class AgeException extends MyUserException{
    public AgeException() {
        super();
    }

    public AgeException(String message) {
        super(message);
    }
}
