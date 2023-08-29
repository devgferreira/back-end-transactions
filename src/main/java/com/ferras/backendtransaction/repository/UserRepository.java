package com.ferras.backendtransaction.repository;

import com.ferras.backendtransaction.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer> {
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(String id);
}
