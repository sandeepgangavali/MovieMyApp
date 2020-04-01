package com.moviecruiser.AuthenticationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviecruiser.AuthenticationService.models.AuthenticationRequest;

@Repository
public interface LoginRepository extends JpaRepository<AuthenticationRequest, String> {
	
	public AuthenticationRequest findByUsername(String username);

}
