package com.payments.controller;

import com.payments.model.PaymentsWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/api/v1/payments")

public class PaymentsController{

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PaymentsWrapper> newPayment(@RequestBody PaymentsWrapper payments){
        return new ResponseEntity<>(payments, HttpStatus.CREATED);
    }
}
