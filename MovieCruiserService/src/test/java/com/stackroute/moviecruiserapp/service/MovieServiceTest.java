package com.stackroute.moviecruiserapp.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.repository.MovieRepository;

import junit.framework.Assert;

public class MovieServiceTest {
	
	Movie movie;

    //Create a mock for UserRepository
    @Mock//test double
    MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;



    List<Movie> list= null;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        movie = new Movie("12","KGF","Superhit",9,"wwww.kgf","awesome","itcuser");
        list = new ArrayList<>();
        list.add(movie);
    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void addMovieTestSuccess() throws Exception {
        when(movieRepository.save(any())).thenReturn(movie);
        Movie savedMovie = movieService.addMovie(movie);
        Assert.assertEquals(movie,savedMovie);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void getAllMovies(){

        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findByUsername(anyString())).thenReturn(list);
        List<Movie> movielist = movieService.getAllMovies(anyString());
        Assert.assertEquals(list,movielist);
        verify(movieRepository,times(1)).findByUsername(anyString());
}
    

    
    @SuppressWarnings("deprecation")
   	@Test
    public void updateMovieTest() throws Exception {
        when(movieRepository.save((Movie)any())).thenReturn(movie);
        when(movieRepository.findByUsername(anyString())).thenReturn(list);
        when(movieService.getMovieById(anyString())).thenReturn(java.util.Optional.ofNullable(movie));
        Movie updatedMovie = movieService.updateMovie(movie,anyString());
        Assert.assertEquals(movie.getComments(),updatedMovie.getComments());
        verify(movieRepository,times(1)).save(movie);
    } 
    
    @Test
    public void deleteMovie() throws Exception {
    	when(movieRepository.existsById(movie.getId())).thenReturn(true);
    	when(movieService.getMovieById(movie.getId())).thenReturn(java.util.Optional.ofNullable(movie));
    	movieService.deleteMovie(anyString());
    	verify(movieRepository,times(1)).deleteById(anyString());
       
    }

	
	}
