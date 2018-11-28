package com.payments.domain;

public class CreditCardResponseWrapper {

    private CreditCardResponse payment;

    public CreditCardResponse getCreditCardResponse() {
        return payment;
    }

    public void setCreditCardResponse(CreditCardResponse creditCardResponse) {
        this.payment = creditCardResponse;
    }
}
