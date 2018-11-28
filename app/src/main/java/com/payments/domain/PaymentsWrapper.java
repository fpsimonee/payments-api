package com.payments.domain;

import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;

public class PaymentsWrapper {

    @Id
    public ObjectId _id;
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
