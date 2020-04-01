package com.stackroute.moviecruiserapp.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.moviecruiserapp.domain.Movie;

@Repository
public interface MovieRepository extends MongoRepository< Movie,String> {



	public List<Movie> findByUsername(String username);
}
