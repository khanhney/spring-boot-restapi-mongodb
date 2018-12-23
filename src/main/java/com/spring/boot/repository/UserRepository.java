package com.spring.boot.repository;

import com.spring.boot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    @Override
    void delete(User user);

}
