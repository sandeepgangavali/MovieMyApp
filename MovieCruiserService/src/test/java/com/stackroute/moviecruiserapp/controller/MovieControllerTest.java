package com.stackroute.moviecruiserapp.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.service.MovieService;
import com.stackroute.moviecruiserapp.util.ValidateMethodUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieController.class)
@AutoConfigureMockMvc
public class MovieControllerTest<when> {

	private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW5kZWVwIiwiZXhwIjoxNTg1MjU2NDUxLCJpYXQiOjE1ODUyMjA0NTF9.8bQyPYYFjzu8mGgRJMB-ssSSLtDbJY1JMZorgloXfZM";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ValidateMethodUtil validateMethodUtil;

	private Movie movie;

	@MockBean
	private MovieService movieService;
	@InjectMocks
	private MovieController movieController;

	private List<Movie> list = null;
	private List<Movie> resultList = null;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
		movie = new Movie("12", "KGF", "Superhit", 9, "wwww.kgf", "awesome", "itcuser");
		list = new ArrayList();
		list.add(movie);
		System.out.println(asJsonString(movie));

	}

	@Test
	public void getAllMovies() throws Exception {
		when(movieService.getAllMovies(anyString())).thenReturn(list);
		when(validateMethodUtil.validateToken(token)).thenReturn(true);
		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.get("/movie").header("Authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)));

		result.andExpect(status().isOk()).andDo(print());

	}
	
	@Test
    public void saveUser() throws Exception {
        when(movieService.addMovie(any())).thenReturn(movie);
        when(validateMethodUtil.validateToken(token)).thenReturn(true);
        when(validateMethodUtil.validateToken(token)).thenReturn(true);
		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.post("/movie").header("Authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)));
	
		
		result.andExpect(status().isCreated()).andDo(print());
		verify(movieService,times(1)).addMovie(any());
	
	}

	
	 @Test
	    public void updateMovie() throws Exception {
	        movie.setComments("f");
	        when(movieService.updateMovie(movie, movie.getId())).thenReturn(movie);
	        when(validateMethodUtil.validateToken(token)).thenReturn(true);
	        ResultActions result = mockMvc
					.perform(MockMvcRequestBuilders.put("/movie/12").header("Authorization", "Bearer " + token)
							.contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)));
	       
	        result.andExpect(status().isOk()).andDo(print());
	        
	        verify(movieService,times(1)).updateMovie(movie,movie.getId());
	        
	    }
	
	
	 @Test
	    public void deleteMovie() throws Exception {
	     movieService.deleteMovie(movie.getId());
	     verify(movieService,times(1)).deleteMovie(movie.getId());
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
