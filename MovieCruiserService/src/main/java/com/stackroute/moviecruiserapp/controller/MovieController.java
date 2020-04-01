package com.stackroute.moviecruiserapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.moviecruiserapp.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiserapp.service.MovieService;
import com.stackroute.moviecruiserapp.util.ValidateMethodUtil;

import io.swagger.annotations.ApiOperation;

@RestController 
@CrossOrigin(origins = "*")
public class MovieController {

	Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	MovieService movieService;

	@Autowired
	ValidateMethodUtil validateMethodUtil;

	@GetMapping("movie")
	@ApiOperation(value = "Returns all the movies from DB", notes = "Provided to retrieve all favorite movies", response = Movie.class)
	public ResponseEntity<?> getAllMovies(@RequestHeader("Authorization") String headerValue) {
		String jwt = headerValue.substring(7);
		if (validateMethodUtil.validateToken(jwt)) {
			logger.trace("Enterered Controller getAllMovies() method");
			String user = validateMethodUtil.extractUsername(jwt);
			List<Movie> movieList = movieService.getAllMovies(user);
			ResponseEntity responseEntity = new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
			return responseEntity;
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Message");
		}

	}

	@PostMapping("movie")
	@ApiOperation(value = "Posts the movie to DB", notes = "Provided to save all favorite movies", response = Movie.class)
	public ResponseEntity<?> postMovie(@RequestBody Movie movie, @RequestHeader("Authorization") String headerValue) {
		String jwt = headerValue.substring(7);
		ResponseEntity responseEntity=null;
		if (validateMethodUtil.validateToken(jwt)) {
			logger.trace("Enterered Controller postMovie() method");
			String user = validateMethodUtil.extractUsername(jwt);
			movie.setUsername(user);
			Movie savedMovie;
			try {
				savedMovie = movieService.addMovie(movie);
			 responseEntity = new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);
			} catch (MovieAlreadyExistsException e) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Message");
			}
			
   
		}
		  return responseEntity;
	}

	@DeleteMapping("movie/{id}")
	@ApiOperation(value = "Delete favorite movies from DB based on key", notes = "Provided to  delete  favorite movies from DB by key")
	public void deleateMovie(@PathVariable String id, @RequestHeader("Authorization") String headerValue) {
		String jwt = headerValue.substring(7);
		if (validateMethodUtil.validateToken(jwt)) {
			logger.trace("Enterered Controller deleateMovie() method");
			try {
				movieService.deleteMovie(id);
			
			} catch (MovieNotFoundException e) {
				
			}
		}
		
			
	}

	@PutMapping("movie/{id}")
	@ApiOperation(value = "Update favorite movies in DB based on key", notes = "Provided to  update  favorite movies from DB by key", response = Movie.class)
	public ResponseEntity<?> updateMovie(@PathVariable(value = "id") String id, @RequestBody Movie comments,
			@RequestHeader("Authorization") String headerValue) {

		String jwt = headerValue.substring(7);
		ResponseEntity responseEntity=null;

		if (validateMethodUtil.validateToken(jwt)) {
			logger.trace("Enterered Controller updateMovie() method");
			Movie savedMovie;
			
			try {
				savedMovie = movieService.updateMovie(comments, id);
				 responseEntity = new ResponseEntity<Movie>(savedMovie, HttpStatus.OK);
				return responseEntity;
			} catch (MovieNotFoundException e) {
				responseEntity= ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Message");
			}
			 
		} 
		return responseEntity;
	}

}
