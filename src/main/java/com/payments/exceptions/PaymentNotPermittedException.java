package com.payments.exceptions;

public class PaymentNotPermittedException extends Exception{

    public PaymentNotPermittedException(String message){
        super(message);
    }
}
