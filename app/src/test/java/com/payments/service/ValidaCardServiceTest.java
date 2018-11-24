package com.payments.service;

import com.payments.domain.Payments;
import com.payments.exceptions.CardNumberException;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.test.util.AssertionErrors;

import static org.junit.Assert.*;

public class ValidaCardServiceTest {

    ValidaCardService cardService = new ValidaCardService();

    @Test
    public void itExists() {
        Result result = JUnitCore.runClasses(ValidaCardService.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    @Test
    public void validCard() throws Exception {
        //GIVEN
        Payments payments = new Payments();
        payments.setCardNumber("378282246310005");


        //WHEN
        boolean result = cardService.validCC(payments.getCardNumber());

        //THEN
        assertTrue(result);
    }

    @Test(expected = CardNumberException.class)
    public void indvalidCard() throws CardNumberException {
        //GIVEN
        Payments payments = new Payments();
        payments.setCardNumber("37828224631");


        //WHEN
        boolean result = cardService.validCC(payments.getCardNumber());

        //THEN
        assertNull(result);
    }
}
