package com.payments.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;

import com.payments.PaymentsApiApplicationTests;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = { PaymentsController.class })
public class PaymentsControllerTests extends PaymentsApiApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private PaymentsController paymentController;

    @Autowired
    private PaymentsControllerAdvice paymentsControllerAdvice;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(paymentController)
                .setControllerAdvice(paymentsControllerAdvice)
                .build();
    }

    @Test
    public void testPOSTSavePaymentsControllerTest() throws Exception {
        final InputStream inputRequest = this.getClass().getClassLoader()
                .getResourceAsStream("post-payment-sucess.json");
        final InputStream inputResponse = this.getClass().getClassLoader()
                .getResourceAsStream("post-payment-sucess.json");

        final String request = IOUtils.toString(inputRequest, "UTF-8");
        final String response =IOUtils.toString(inputResponse, "UTF-8");

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(content().json(response)).andExpect(status().isOk());
    }
}
