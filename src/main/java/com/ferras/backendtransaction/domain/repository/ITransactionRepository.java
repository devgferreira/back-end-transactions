package com.ferras.backendtransaction.domain.repository;

import com.ferras.backendtransaction.domain.transaction.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ITransactionRepository extends MongoRepository<Transaction, Integer> {
}
