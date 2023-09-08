package com.ferras.backendtransaction.application.interfaces;
import com.ferras.backendtransaction.application.dtos.UserDTO;
import com.ferras.backendtransaction.domain.model.user.User;

import java.math.BigDecimal;
import java.util.List;

public interface IUserService {
    void validateTransaction(UserDTO senderDTO, BigDecimal amount) throws Exception;
    UserDTO findUserById(String id) throws Exception;
    void saveUser(UserDTO userDTO);
    UserDTO createUser(UserDTO userDTO) throws Exception;
    List<UserDTO> getAllUsers();

    UserDTO findUserByDocument(String document) throws Exception;
}
