package com.ferras.backendtransaction.interfaces;
import com.ferras.backendtransaction.domain.user.User;
import java.math.BigDecimal;

public interface IUserService {
    void validateTransaction(User sender, BigDecimal amount);
    User findUserById(int id);
    User saveUser(User user);
}
