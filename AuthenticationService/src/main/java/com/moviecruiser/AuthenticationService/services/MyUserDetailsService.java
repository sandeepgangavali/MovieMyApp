package com.moviecruiser.AuthenticationService.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moviecruiser.AuthenticationService.models.AuthenticationRequest;
import com.moviecruiser.AuthenticationService.repository.LoginRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private LoginRepository logInRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		
		AuthenticationRequest authenticationRequest=logInRepo.findByUsername(userName);
		
		
		return new User(authenticationRequest.getUsername(),authenticationRequest.getPassword(),new ArrayList<>());
	}

	public AuthenticationRequest registerUser(AuthenticationRequest authenticationRequest) {
		AuthenticationRequest savedCredentials=logInRepo.save(authenticationRequest);
		return savedCredentials;
	}

}
