package com.ferras.backendtransaction.domain.repository;

import com.ferras.backendtransaction.domain.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, Integer> {
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(int id);
}
