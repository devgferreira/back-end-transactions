package com.ferras.backendtransaction.application.service;

import com.ferras.backendtransaction.application.interfaces.IUserService;
import com.ferras.backendtransaction.domain.enums.UserType;
import com.ferras.backendtransaction.domain.repository.IUserRepository;
import com.ferras.backendtransaction.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class UserService implements IUserService {

    private final IUserRepository _userRepository;
    @Autowired
    public UserService(IUserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo lojisa não está autorizado a realizar transação");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    @Override
    public User findUserById(Long id) throws Exception {
        return  _userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    @Override
    public void saveUser(User user) {
        _userRepository.save(user);
    }
}
