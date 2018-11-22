package com.payments.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class Payments {

    @JsonProperty(value = "clientId", required = true) private String clientId;
    @JsonProperty(value = "buyer", required = true) private Buyer buyer;
    @JsonProperty(value = "payment", required = true) private Payment payment;
    @JsonProperty(value = "card") private Optional<Card> card;

    public Payments(){
        super();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientID) {
        this.clientId = clientID;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Optional<Card> getCard() {
        return card;
    }

    public void setCard(Optional<Card> card) {
        this.card = card;
    }
}
