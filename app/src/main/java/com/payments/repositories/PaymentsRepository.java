package com.payments.repositories;

import com.payments.domain.PaymentsWrapper;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentsRepository extends MongoRepository<PaymentsWrapper, String> {

    PaymentsWrapper findBy_id(ObjectId  _id);

    PaymentsWrapper findAllBy_id(Sort.Direction desc, String id);
}
