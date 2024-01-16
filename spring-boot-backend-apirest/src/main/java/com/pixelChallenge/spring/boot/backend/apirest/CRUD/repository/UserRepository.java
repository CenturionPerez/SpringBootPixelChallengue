package com.pixelChallenge.spring.boot.backend.apirest.CRUD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pixelChallenge.spring.boot.backend.apirest.CRUD.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer>{

}
