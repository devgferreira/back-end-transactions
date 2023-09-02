package com.ferras.backendtransaction.application.interfaces;
import com.ferras.backendtransaction.domain.model.user.User;
import java.math.BigDecimal;

public interface IUserService {
    void validateTransaction(User sender, BigDecimal amount) throws Exception;
    User findUserById(Long id) throws Exception;
    User saveUser(User user);
}
