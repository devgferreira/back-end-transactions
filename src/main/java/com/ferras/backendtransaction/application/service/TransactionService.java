package com.ferras.backendtransaction.application.service;

import com.ferras.backendtransaction.application.dtos.TransactionDTO;
import com.ferras.backendtransaction.application.dtos.UserDTO;
import com.ferras.backendtransaction.application.interfaces.ITransactionService;
import com.ferras.backendtransaction.application.interfaces.IUserService;
import com.ferras.backendtransaction.domain.model.transaction.Transaction;
import com.ferras.backendtransaction.domain.model.user.User;
import com.ferras.backendtransaction.domain.repository.ITransactionRepository;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public TransactionService(ITransactionRepository _transactionRepository, IUserService _userService, ModelMapper modelMapper) {
        this._transactionRepository = _transactionRepository;
        this._userService = _userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createTransaction(TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        UserDTO senderDTO = _userService.findUserById(transaction.getSender().getId());
        UserDTO receiverDTO = _userService.findUserById(transactionDTO.getReceiver().getId());

        User sender = modelMapper.map(senderDTO, User.class);
        User receiver = modelMapper.map(receiverDTO, User.class);

        _userService.validateTransaction(senderDTO, transaction.getAmount());

        boolean isAuthorized =!authorizeTransaction(receiverDTO, transactionDTO.getAmount());
        if(!isAuthorized){
            throw new Exception("Transação não autorizado");
        }
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        senderDTO.setBalance(senderDTO.getBalance().subtract(transactionDTO.getAmount()));
        receiverDTO.setBalance(receiverDTO.getBalance().add(transactionDTO.getAmount()));

        _transactionRepository.save(transaction);
        _userService.saveUser(senderDTO);
        _userService.saveUser(receiverDTO);

    }

    @Override
    public boolean authorizeTransaction(UserDTO senderDTO, BigDecimal value) {
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
