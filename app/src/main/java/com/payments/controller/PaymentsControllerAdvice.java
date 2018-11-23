package com.payments.controller;

import com.payments.domain.ErrorWrapper;
import com.payments.domain.Error;
import com.payments.exceptions.CardNumberException;
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
        errorWrapper.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorWrapper.setStatusMessage("Bad Request");
        errorWrapper.setException(e.getClass().toString());
        errorWrapper.setMessage("Card number is invalid, please check this");
        logger.error("Error on validate card number");

        return error(errorWrapper, HttpStatus.BAD_REQUEST);
    }
}
