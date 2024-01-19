package com.svvj.rajbika.rajbikaapi.users.repository;

import com.svvj.rajbika.rajbikaapi.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}

