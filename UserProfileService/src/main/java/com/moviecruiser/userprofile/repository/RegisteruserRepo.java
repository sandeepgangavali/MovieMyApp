package com.moviecruiser.userprofile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.moviecruiser.userprofile.model.Register;


@Repository
public interface RegisteruserRepo extends MongoRepository<Register,String> {

}
