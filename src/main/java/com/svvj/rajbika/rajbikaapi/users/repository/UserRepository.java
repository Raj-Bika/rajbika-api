package com.svvj.rajbika.rajbikaapi.users.repository;

import com.svvj.rajbika.rajbikaapi.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String emil);
}

