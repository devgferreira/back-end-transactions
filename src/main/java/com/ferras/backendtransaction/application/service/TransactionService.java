package com.ferras.backendtransaction.application.service;

import com.ferras.backendtransaction.application.dtos.TransactionDTO;
import com.ferras.backendtransaction.application.interfaces.ITransactionService;
import com.ferras.backendtransaction.application.interfaces.IUserService;
import com.ferras.backendtransaction.domain.model.transaction.Transaction;
import com.ferras.backendtransaction.domain.model.user.User;
import com.ferras.backendtransaction.domain.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService implements ITransactionService {
    private final ITransactionRepository _transactionRepository;
    private final IUserService _userService;

    private RestTemplate restTemplate;

    @Autowired
    public TransactionService(ITransactionRepository _transactionRepository, IUserService _userService) {
        this._transactionRepository = _transactionRepository;
        this._userService = _userService;
    }

    @Override
    public void createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = _userService.findUserById(transactionDTO.getSender().getId());
        User receiver = _userService.findUserById(transactionDTO.getReceiver().getId());

        _userService.validateTransaction(sender, transactionDTO.getAmount());

        boolean isAuthorized =!authorizeTransaction(sender, transactionDTO.getAmount());
        if(!isAuthorized){
            throw new Exception("Transação não autorizado");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.getAmount()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.getAmount()));

        _transactionRepository.save(transaction);
        _userService.saveUser(sender);
        _userService.saveUser(receiver);

    }

    @Override
    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(
                "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
                Map.class);
        if(authorizationResponse.getStatusCode() == HttpStatus.OK &&
                authorizationResponse.getBody().get("message") == "Autorizado"){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else {
            return false;
        }
    }
}
