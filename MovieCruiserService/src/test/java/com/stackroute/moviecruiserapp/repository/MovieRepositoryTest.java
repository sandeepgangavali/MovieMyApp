package com.stackroute.moviecruiserapp.repository;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.stackroute.moviecruiserapp.domain.Movie;




@RunWith(SpringRunner.class)
@DataMongoTest
public class MovieRepositoryTest {
	
	@Autowired
	    MovieRepository movieRepository;
	 
	 
	    Movie movie1,movie2;
	    List<Movie> list= null;

	    @Before
	    public void setUp()
	    {
	    	MockitoAnnotations.initMocks(this);
	        movie1 = new Movie("12","KGF","Superhit",9,"wwww.kgf","awesome","itcuser");
	        movie2 = new Movie("13","KGF1","Superhit5",45,"wwww.kgf1","awesome1","itcuser1");
	        list = new ArrayList<>();
	        list.add(movie1);
	        list.add(movie2);
	    } 
	    
	    
	    @After
	    public void tearDown(){

	    	movieRepository.deleteAll();
	    }
	    
	    
	    
	    @Test
	    public void saveMovieTest(){
	    	movieRepository.save(movie1);
	    	List<Movie> fetchMovie = movieRepository.findByUsername("itcuser");
	    	Assert.assertEquals("awesome",fetchMovie.get(0).getComments());
	    	
	    	}
	    
	    @Test
	    public void getAllUserTest(){
	    
	    	movieRepository.save(movie1);
	    	movieRepository.save(movie2);

	        List<Movie> list = movieRepository.findAll();
	        Assert.assertEquals("KGF",list.get(0).getTitle());
	    
	   }
	    
	    
	    @Test
	    public void updateMovieTest() {
	        movieRepository.save(movie1);
	        List<Movie> fetchMovie=movieRepository.findByUsername("itcuser");
	        movie1=fetchMovie.get(0);
	        movie1.setComments("Sending");
	        movieRepository.save(movie1);
	        Assert.assertEquals("Sending", movie1.getComments());
	    }
	    
	    
	    @Test
	    public void deleteMovieTest(){
	        movieRepository.save(movie1);
	        movieRepository.deleteById("12");
	        boolean fetchMovie = movieRepository.existsById(movie1.getId());
	        Assert.assertEquals(false,fetchMovie);
	    }
}
