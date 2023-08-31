package com.ferras.backendtransaction.repository;

import com.ferras.backendtransaction.domain.transaction.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TransactionRepository extends MongoRepository<Transaction, Integer> {
}
