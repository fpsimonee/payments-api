package com.payments.domain;

public class TicketResponseWrapper {

    private TicketResponse payment;

    public TicketResponse getTicketResponse() {
        return payment;
    }

    public void setTicketResponse(TicketResponse ticketResponse) {
        this.payment = ticketResponse;
    }
}
