package com.payments.service;

import com.payments.domain.Payments;
import com.payments.domain.PaymentsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserService {


    public static Payments toObject(PaymentsWrapper payments){
        Payments novo = new Payments();

        novo.setClientId(payments.getPayment().getClientId());
        novo.setCpf(payments.getPayment().getCpf());
        novo.setName(payments.getPayment().getName());
        novo.setEmail(payments.getPayment().getEmail());
        novo.setAmount(payments.getPayment().getAmount());
        novo.setType(payments.getPayment().getType());
        novo.setCardNumber(payments.getPayment().getCardNumber());
        novo.setCardName(payments.getPayment().getCardName());
        novo.setExpirationDate(payments.getPayment().getExpirationDate());
        novo.setCvv(payments.getPayment().getCvv());
        novo.setTicketNumber(payments.getPayment().getTicketNumber());

        return novo;
    }
}
