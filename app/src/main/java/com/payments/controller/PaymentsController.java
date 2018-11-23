package com.payments.controller;

import com.payments.domain.PaymentsWrapper;
import com.payments.service.ValidaCardService;
import com.payments.exceptions.CardNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")

public class PaymentsController{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ValidaCardService cardService;

    @RequestMapping(value="/payments", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<PaymentsWrapper> addNewPayment(@RequestBody PaymentsWrapper payments) throws CardNumberException {
        String paymentType = payments.getPayment().getType();
        String cardNumber = payments.getPayment().getCardNumber()+payments.getPayment().getCvv();

            if(paymentType.equals("creditCard")){
                cardService.validCC(cardNumber);
            }

            logger.info("Payment Add");
            return new ResponseEntity<>(payments, HttpStatus.CREATED);

    }
}
