package com.ferras.backendtransaction.repository;

import com.ferras.backendtransaction.domain.transaction.Transaction;
import com.ferras.backendtransaction.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TransactionRepository extends MongoRepository<Transaction, Integer> {
}
