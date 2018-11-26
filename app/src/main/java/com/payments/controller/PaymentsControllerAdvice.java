package com.payments.controller;

import com.payments.domain.ErrorWrapper;
import com.payments.domain.Error;
import com.payments.exceptions.CardNumberException;
import com.payments.exceptions.CvvFormatException;
import com.payments.exceptions.PaymentNotPermittedException;
import com.payments.exceptions.TicketLengthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
@RequestMapping(produces = "application/json")
public class PaymentsControllerAdvice extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ResponseEntity<ErrorWrapper> error(final Error error, HttpStatus httpStatus) {
        return new ResponseEntity<ErrorWrapper>(new ErrorWrapper(error), httpStatus);
    }

    @ResponseBody
    @ExceptionHandler(CardNumberException.class)
    public ResponseEntity<ErrorWrapper> response(final CardNumberException e){
        Error errorWrapper = new Error();
        errorWrapper.setStatusCode(HttpStatus.NOT_ACCEPTABLE.toString());
        errorWrapper.setStatusMessage("Bad Request");
        errorWrapper.setException(e.getClass().toString());
        errorWrapper.setMessage("Card number is invalid, please check this");
        logger.error("Error on validate card number");

        return error(errorWrapper, HttpStatus.NOT_ACCEPTABLE);
    }

    @ResponseBody
    @ExceptionHandler(TicketLengthException.class)
    public ResponseEntity<ErrorWrapper> response(final TicketLengthException e){
        Error errorWrapper = new Error();
        errorWrapper.setStatusCode(HttpStatus.NOT_ACCEPTABLE.toString());
        errorWrapper.setStatusMessage("Ticket Invalid");
        errorWrapper.setException(e.getClass().toString());
        errorWrapper.setMessage("Ticket length is less then 40 characters");
        logger.error("Ticket length is less then 40 characters");

        return error(errorWrapper, HttpStatus.NOT_ACCEPTABLE);
    }

    @ResponseBody
    @ExceptionHandler(PaymentNotPermittedException.class)
    public ResponseEntity<ErrorWrapper> response(final PaymentNotPermittedException e){
        Error errorWrapper = new Error();
        errorWrapper.setStatusCode(HttpStatus.NOT_ACCEPTABLE.toString());
        errorWrapper.setStatusMessage("Payment not Permitted");
        errorWrapper.setException(e.getClass().toString());
        errorWrapper.setMessage("type of Payment not Permitted");
        logger.error("type of Payment not Permitted");

        return error(errorWrapper, HttpStatus.NOT_ACCEPTABLE);
    }

    @ResponseBody
    @ExceptionHandler(CvvFormatException.class)
    public ResponseEntity<ErrorWrapper> response(final CvvFormatException e){
        Error errorWrapper = new Error();
        errorWrapper.setStatusCode(HttpStatus.NOT_ACCEPTABLE.toString());
        errorWrapper.setStatusMessage("Payment not Acceptable");
        errorWrapper.setException(e.getClass().toString());
        errorWrapper.setMessage("CVV is null");
        logger.error("CVV is null or short that 3");

        return error(errorWrapper, HttpStatus.NOT_ACCEPTABLE);
    }
}
