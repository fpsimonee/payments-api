package com.payments.domain;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payments")
public class Payments {

    @Indexed(unique = true)
    private String clientId;

    private String name;
    private String email;
    private String cpf;
    private String amount;
    private String type;
    private String ticketNumber;
    private String cardName;
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public Payments(){
        super();
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String boletoNumber) {
        this.ticketNumber = boletoNumber;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientID) {
        this.clientId = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
