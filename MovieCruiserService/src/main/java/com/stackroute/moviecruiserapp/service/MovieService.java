package com.stackroute.moviecruiserapp.service;

import java.util.List;

import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.moviecruiserapp.exceptions.MovieNotFoundException;

public interface MovieService {

	public List<Movie> getAllMovies(String username);

	public Movie addMovie(Movie movie) throws MovieAlreadyExistsException;

	public void deleteMovie(String id)throws MovieNotFoundException ;

	public Movie updateMovie(Movie movie, String id) throws MovieNotFoundException;

}
