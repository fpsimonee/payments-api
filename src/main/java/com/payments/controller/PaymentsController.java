package com.payments.controller;

import com.payments.domain.*;
import com.payments.exceptions.CardNumberException;
import com.payments.exceptions.CvvFormatException;
import com.payments.exceptions.TicketLengthException;
import com.payments.service.ParserService;
import com.payments.service.ValidaCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")

public class PaymentsController{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/payments", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> response(@RequestBody PaymentsWrapper request) throws CardNumberException, CvvFormatException, TicketLengthException {

        Payments pay = ParserService.toObject(request);
        String cardNumber = pay.getCardNumber();

            if(pay.getType().equals("creditCard")) {
                // CVV length is 3
                if(pay.getCvv() == null || pay.getCvv().length()<3 || pay.getCvv().length()>3){
                    logger.error("payment without CVV");
                    throw new CvvFormatException("CVV is not null");
                }
                // Credit Card length 16
                if(cardNumber.length()<16){
                    logger.error("payment without CVV");
                    throw new CardNumberException("card number is short that 16");
                }else{
                    logger.info("Call Validate Credit Card");
                    ValidaCardService.validCC(cardNumber);
                }

                CreditCardResponse newResponse = new CreditCardResponse();

                newResponse.setStatus("ok");
                newResponse.setCreditCard(cardNumber);

                CreditCardResponseWrapper response = new CreditCardResponseWrapper();
                response.setCreditCardResponse(newResponse);

                logger.info("Payment Add");
                return new ResponseEntity<>(response, HttpStatus.CREATED);

            }else{
                TicketResponse newResponse = new TicketResponse();

                if(pay.getTicketNumber().length() != 47){
                    logger.error("Length is: "+pay.getTicketNumber().length());
                    throw new TicketLengthException("Lenght is diferent 44");
                }else{
                    logger.info("Ticket number is: "+pay.getTicketNumber());

                    newResponse.setTicketNumber(pay.getTicketNumber());

                    TicketResponseWrapper response = new TicketResponseWrapper();
                    response.setTicketResponse(newResponse);

                    logger.info("Payment Add");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }

            }



    }
}
