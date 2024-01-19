package com.svvj.rajbika.rajbikaapi.usercart.repository;

import com.svvj.rajbika.rajbikaapi.usercart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCartRepository extends MongoRepository<Cart, String> {

}
