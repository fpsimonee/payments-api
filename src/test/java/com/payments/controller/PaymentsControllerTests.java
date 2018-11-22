package com.payments.controller;

import com.payments.PaymentsApiApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PaymentsControllerTests extends PaymentsApiApplicationTests{

    private MockMvc mockMvc;

    @Autowired
    private PaymentsController paymentController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    public void testPOSTSavePaymentsControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
