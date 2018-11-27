package com.payments.controller;

import com.payments.domain.*;
import com.payments.exceptions.CardNumberException;
import com.payments.exceptions.CvvFormatException;
import com.payments.exceptions.PaymentNotPermittedException;
import com.payments.exceptions.TicketLengthException;
import com.payments.repositories.PaymentsRepository;
import com.payments.service.ParserService;
import com.payments.service.ValidaCardService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")

public class PaymentsController{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaymentsRepository repository;


    @RequestMapping(value="/payments", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Object CreatePayment(@RequestBody PaymentsWrapper request) throws CardNumberException, CvvFormatException, TicketLengthException, PaymentNotPermittedException {

        Payment pay = ParserService.toObject(request);


            if(pay.getType().equals("creditCard")) {
                return this.CreditCardResponse(pay, request);
            }else{
                return this.TicketResponse(pay, request);
            }
    }


    public CreditCardResponseWrapper CreditCardResponse(Payment pay, PaymentsWrapper request) throws CardNumberException, CvvFormatException {

        String cardNumber = pay.getCardNumber();
        // CVV length is 4
        if(pay.getCvv() == null || pay.getCvv().length()<3 || pay.getCvv().length()>4){
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

//        PaymentsWrapper teste = repository.findAllBy_id(Sort.Direction.DESC, "_id");
//        logger.info("last cod find is "+teste.getPayment().getCode());
        request.set_id(ObjectId.get());
        request.getPayment().setStatus("Processing");
        repository.save(request);
        logger.info("Payment Add");

        CreditCardResponse newResponse = new CreditCardResponse();

        newResponse.setStatus("ok");
        newResponse.setCreditCard(cardNumber);

        CreditCardResponseWrapper response = new CreditCardResponseWrapper();
        response.setCreditCardResponse(newResponse);


        return response;
    }


    public TicketResponseWrapper TicketResponse(Payment pay, PaymentsWrapper request) throws TicketLengthException, PaymentNotPermittedException{
        if(pay.getType().equals("ticket")){
            TicketResponse newResponse = new TicketResponse();
            if(pay.getTicketNumber().length() != 47){
                logger.error("Length is: "+pay.getTicketNumber().length());
                throw new TicketLengthException("Lenght is diferent 44");
            }else{
                logger.info("Ticket number is: "+pay.getTicketNumber());
                request.set_id(ObjectId.get());
                request.getPayment().setStatus("Processing");
                repository.save(request);

                newResponse.setTicketNumber(pay.getTicketNumber());

                TicketResponseWrapper response = new TicketResponseWrapper();
                response.setTicketResponse(newResponse);

                logger.info("Payment Add");
                return response;
            }
        }else{
            logger.error("Payment not Permitted");
            throw new PaymentNotPermittedException("Payment Not Permitted");
        }

    }



    @RequestMapping(value = "/payments/{id}/status", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<PaymentStatusWrapper> getStatus(@PathVariable(value="id") String id){

        logger.info("Payment requested is "+ id);

        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setId("123456");
        paymentStatus.setName("Felipe Simone");
        paymentStatus.setStatus("Processing");

        PaymentStatusWrapper response = new PaymentStatusWrapper();
        response.setPaymentStatus(paymentStatus);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/payments/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public PaymentsWrapper getPaymentsById(@PathVariable("id") String id){
        ObjectId _id = new ObjectId(id);
        return repository.findBy_id(_id);
    }
}
