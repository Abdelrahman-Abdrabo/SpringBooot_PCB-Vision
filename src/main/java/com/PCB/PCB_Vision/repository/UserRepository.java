package com.PCB.PCB_Vision.repository;

import com.PCB.PCB_Vision.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    // Custom query to find a user by email and password
    @Query("{ 'email': ?0, 'password': ?1 }")
    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

}
