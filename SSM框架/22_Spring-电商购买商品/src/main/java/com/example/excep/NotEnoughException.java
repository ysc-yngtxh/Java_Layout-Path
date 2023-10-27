package com.example.excep;


/**
 * @author 游家纨绔
 */
// 自定义的运行时异常
public class NotEnoughException extends RuntimeException{
    public NotEnoughException(){
        super();
    }

    public NotEnoughException(String message){
        super(message);
    }
}
