package com.rudy.ryanto.user.management.exception;

public class CustomerException extends RuntimeException{
    public CustomerException(String message){super(message);}

    public CustomerException(String message, Throwable throwable){super(message,throwable);}
}
