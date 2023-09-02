package com.ferras.backendtransaction.application.interfaces;

import com.ferras.backendtransaction.domain.model.transaction.Transaction;
import com.ferras.backendtransaction.domain.model.user.User;

import java.math.BigDecimal;

public interface ITransactionService {
    void createTransaction(Transaction transaction);
    boolean authorizeTransaction(User sender, BigDecimal value);
}
