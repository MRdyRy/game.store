package com.rudy.ryanto.game.product.exception;

public class GameProductException extends RuntimeException{
    public GameProductException(String message){super(message);}

    public GameProductException(String message, Throwable throwable){super(message,throwable);}
}
