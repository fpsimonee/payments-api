package com.payments.controller;


import com.payments.PaymentsApiApplicationTests;
import com.payments.exceptions.CardNumberException;
import com.payments.exceptions.CvvFormatException;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.io.InputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PaymentsControllerTests extends PaymentsApiApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private PaymentsController paymentsController;

    @Autowired
    private PaymentsControllerAdvice paymentsControllerAdvice;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(paymentsController)
                .setControllerAdvice(paymentsControllerAdvice)
                .build();
    }

    @Test
    public void testPOSTSavePaymentsControllerTestCreditCardSuccess() throws Exception {
        final InputStream inputRequest = this.getClass().getClassLoader()
                .getResourceAsStream("post-payment-success.json");
        final InputStream inputResponse = this.getClass().getClassLoader()
                .getResourceAsStream("credit-card-response-success.json");

        final String request = IOUtils.toString(inputRequest, "UTF-8");
        final String response =IOUtils.toString(inputResponse, "UTF-8");

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(content().json(response)).andExpect(status().isCreated());
    }

    @Test
    public void testPOSTSavePaymentsControllerTestErrorCVV() throws CvvFormatException, IOException {
        final InputStream inputRequest = this.getClass().getClassLoader()
                .getResourceAsStream("post-payment-without-cvv.json");
        final InputStream inputResponse = this.getClass().getClassLoader()
                .getResourceAsStream("error-406-cvv.json");

        final String request = IOUtils.toString(inputRequest, "UTF-8");
        final String response =IOUtils.toString(inputResponse, "UTF-8");

        try {
            this.mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/v1/payments")
                            .contentType(MediaType.APPLICATION_JSON).content(request))
                    .andExpect(content().json(response)).andExpect(status().isNotAcceptable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPOSTSavePaymentsControllerTestErrorCreditCard() throws CardNumberException, IOException {
        final InputStream inputRequest = this.getClass().getClassLoader()
                .getResourceAsStream("post-payment-error-credit-card-number.json");
        final InputStream inputResponse = this.getClass().getClassLoader()
                .getResourceAsStream("error-400-number-invalid.json");

        final String request = IOUtils.toString(inputRequest, "UTF-8");
        final String response =IOUtils.toString(inputResponse, "UTF-8");

        try {
            this.mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/v1/payments")
                            .contentType(MediaType.APPLICATION_JSON).content(request))
                    .andExpect(content().json(response)).andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPOSTSavePaymentsControllerTestTicketSuccess() throws Exception {
        final InputStream inputRequest = this.getClass().getClassLoader()
                .getResourceAsStream("post-payment-ticket-success.json");
        final InputStream inputResponse = this.getClass().getClassLoader()
                .getResourceAsStream("ticket-response-success.json");

        final String request = IOUtils.toString(inputRequest, "UTF-8");
        final String response =IOUtils.toString(inputResponse, "UTF-8");

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(content().json(response)).andExpect(status().isCreated());
    }

    @Test
    public void testPOSTSavePaymentsControllerTestTicketError() throws Exception {
        final InputStream inputRequest = this.getClass().getClassLoader()
                .getResourceAsStream("post-payment-ticket-invalid.json");
        final InputStream inputResponse = this.getClass().getClassLoader()
                .getResourceAsStream("error-ticket-invalid.json");

        final String request = IOUtils.toString(inputRequest, "UTF-8");
        final String response =IOUtils.toString(inputResponse, "UTF-8");

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(content().json(response)).andExpect(status().isNotAcceptable());
    }
}
