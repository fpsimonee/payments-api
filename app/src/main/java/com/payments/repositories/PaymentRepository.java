package com.payments.repositories;

import com.payments.domain.Payment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    Payment findBy_id(ObjectId _id);

    Payment findByCode(int code);
}
