package com.payments.domain;

import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;

public class PaymentsWrapper {

    @Id
    public ObjectId _id;
    private Payments payment;

    public Payments getPayment() {
        return payment;
    }

    public void setPayment(Payments payment) {
        this.payment = payment;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
