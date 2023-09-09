package com.ferras.backendtransaction.controlleres;

import com.ferras.backendtransaction.application.dtos.TransactionDTO;
import com.ferras.backendtransaction.application.interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final ITransactionService _transactionService;

    @Autowired
    public TransactionController(ITransactionService transactionService) {
        _transactionService = transactionService;
    }


    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        TransactionDTO newTransaction = _transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(newTransaction);
    }

}
