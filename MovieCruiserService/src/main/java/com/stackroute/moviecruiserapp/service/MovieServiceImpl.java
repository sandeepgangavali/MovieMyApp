package com.stackroute.moviecruiserapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.moviecruiserapp.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiserapp.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieRepository movieRepository;

	@Override
	public List<Movie> getAllMovies(String username){
		 return movieRepository.findByUsername(username);
	}

	@Override
	public Movie addMovie(Movie movie) throws MovieAlreadyExistsException {
		 Movie savedmovie =movieRepository.save(movie);
		 return savedmovie;
				
	}

	@Override
	public void deleteMovie(String id) throws MovieNotFoundException {
		movieRepository.deleteById(id);
		
	}
	
	 public Optional<Movie> getMovieById(String id)throws MovieNotFoundException   {
	        Optional<Movie> movie;
	        movie=movieRepository.findById(id);
	        return movie;
	 }

	@Override
	public Movie updateMovie( Movie  mv,String id) throws MovieNotFoundException{
		Optional<Movie> movieup=getMovieById(id);
		movieup.get().setComments(mv.getComments());
		 Movie savedMovie=movieRepository.save(movieup.get());
		 return savedMovie;
	}

}
