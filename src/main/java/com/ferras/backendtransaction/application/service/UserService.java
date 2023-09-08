package com.ferras.backendtransaction.application.service;

import com.ferras.backendtransaction.application.dtos.UserDTO;
import com.ferras.backendtransaction.application.interfaces.IUserService;
import com.ferras.backendtransaction.domain.enums.UserType;
import com.ferras.backendtransaction.domain.repository.IUserRepository;
import com.ferras.backendtransaction.domain.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserRepository _userRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public UserService(IUserRepository userRepository, ModelMapper modelMapper) {
        _userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void validateTransaction(UserDTO senderDTO, BigDecimal amount) throws Exception {
        User sender = modelMapper.map(senderDTO, User.class);

        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo lojisa não está autorizado a realizar transação");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    @Override
    public UserDTO findUserById(String id) throws Exception {
        return modelMapper.map(_userRepository.findUserById(id).orElse(new User()), UserDTO.class);
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        modelMapper.map(_userRepository.save(user), UserDTO.class);
    }
    @Override
    public UserDTO createUser(UserDTO userDTO) throws Exception {
        User user = modelMapper.map(userDTO, User.class);
        if(_userRepository.findUserByDocument(userDTO.getDocument()).isPresent()){
            throw new Exception("Cpf Duplicado");
        }else{
            return modelMapper.map(_userRepository.save(user), UserDTO.class);

        }
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = _userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public UserDTO findUserByDocument(String document) throws Exception {
        if(_userRepository.findUserByDocument(document).isPresent()){
            return modelMapper.map(_userRepository.findUserByDocument(document).
                    orElse(new User()), UserDTO.class);
        }else{
            throw new Exception("User not Found - Document:" + document);
        }
    }
}
