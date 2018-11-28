package com.payments.controller;

import com.payments.domain.*;
import com.payments.exceptions.CardNumberException;
import com.payments.exceptions.CvvFormatException;
import com.payments.exceptions.PaymentNotPermittedException;
import com.payments.exceptions.TicketLengthException;
import com.payments.repositories.PaymentRepository;
import com.payments.service.ParserService;
import com.payments.service.PaymentService;
import com.payments.service.ValidaCardService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")

public class PaymentsController{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private PaymentService servicePay;


    @RequestMapping(value="/payments", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Object CreatePayment(@RequestBody PaymentsWrapper request) throws CardNumberException, CvvFormatException, TicketLengthException, PaymentNotPermittedException {

        Payment pay = ParserService.toObject(request);

            if(pay.getType().equals("creditCard")) {
                return servicePay.AddCreditCard(pay);
            }else{
                return servicePay.AddTicket(pay);
            }
    }


    @RequestMapping(value = "/payments/{code}/status", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Object getPaymentsById(@PathVariable("code") int code) throws ChangeSetPersister.NotFoundException {
        Payment searched = repository.findByCode(code);

        if (searched == null){
            throw new ChangeSetPersister.NotFoundException();
        }

        if(searched.getType().equals("creditCard")){
            CreditCardResponse find = new CreditCardResponse();
            find.setStatus(searched.getStatus());
            find.setCreditCard(searched.getCardNumber());
            find.setCode(searched.getCode());

            CreditCardResponseWrapper response = new CreditCardResponseWrapper();
            response.setCreditCardResponse(find);
            return response;
        }else{
            TicketResponse find = new TicketResponse();
            find.setTicketNumber(searched.getTicketNumber());
            find.setCode(searched.getCode());

            TicketResponseWrapper response = new TicketResponseWrapper();
            response.setTicketResponse(find);
            return response;
        }


    }


}
