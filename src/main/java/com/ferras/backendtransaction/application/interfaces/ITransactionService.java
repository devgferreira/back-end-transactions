package com.ferras.backendtransaction.application.interfaces;

import com.ferras.backendtransaction.application.dtos.TransactionDTO;
import com.ferras.backendtransaction.application.dtos.UserDTO;

import java.math.BigDecimal;

public interface ITransactionService {
    TransactionDTO createTransaction(TransactionDTO transactionDTO) throws Exception;
    boolean authorizeTransaction(UserDTO senderDTO, BigDecimal value);
}
