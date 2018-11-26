package com.payments;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootApplication
public class PaymentsApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(PaymentsApiApplication.class, args);

    }
}
