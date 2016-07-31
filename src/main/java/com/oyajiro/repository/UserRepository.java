package com.oyajiro.repository;

import com.oyajiro.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by user on 31.07.16.
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByLogin(String login);
}
