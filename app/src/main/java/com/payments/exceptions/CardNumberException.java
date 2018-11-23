package com.payments.exceptions;

public class CardNumberException extends Exception{

        public CardNumberException() {

        }

        public CardNumberException(String message) {
            super(message);
        }
}
