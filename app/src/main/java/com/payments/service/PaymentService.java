package com.payments.service;

import com.payments.domain.*;
import com.payments.exceptions.CardNumberException;
import com.payments.exceptions.CvvFormatException;
import com.payments.exceptions.PaymentNotPermittedException;
import com.payments.exceptions.TicketLengthException;
import com.payments.repositories.PaymentRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaymentRepository repository;



    public CreditCardResponseWrapper AddCreditCard(Payment pay) throws CardNumberException, CvvFormatException {

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

        int code = this.getLastCode();
        pay.set_id(ObjectId.get());
        pay.setCode(code);
        pay.setStatus("Processing");
        repository.save(pay);
        logger.info("Payment Add");

        CreditCardResponse newResponse = new CreditCardResponse();

        newResponse.setStatus(pay.getStatus());
        newResponse.setCreditCard(cardNumber);
        newResponse.setCode(code);

        CreditCardResponseWrapper response = new CreditCardResponseWrapper();
        response.setCreditCardResponse(newResponse);


        return response;
    }


    public TicketResponseWrapper AddTicket(Payment pay) throws TicketLengthException, PaymentNotPermittedException {
        if(pay.getType().equals("ticket")){
            TicketResponse newResponse = new TicketResponse();
            if(pay.getTicketNumber().length() != 47){
                logger.error("Length is: "+pay.getTicketNumber().length());
                throw new TicketLengthException("Lenght is diferent 44");
            }else{
                logger.info("Ticket number is: "+pay.getTicketNumber());
                int code = this.getLastCode();
                pay.set_id(ObjectId.get());
                pay.setCode(code);
                pay.setStatus("Processing");
                repository.save(pay);
                logger.info("Payment Add");


                newResponse.setTicketNumber(pay.getTicketNumber());
                newResponse.setCode(code);


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


    public int getLastCode(){
        List<Payment> lastPay = repository.findAll(new Sort(Sort.Direction.DESC, "code"));

        if(lastPay.isEmpty())
            return 1;
        else
            return lastPay.get(0).getCode()+1;
    }
}
