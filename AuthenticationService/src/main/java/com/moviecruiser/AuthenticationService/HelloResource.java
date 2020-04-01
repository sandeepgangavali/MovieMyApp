package com.moviecruiser.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moviecruiser.AuthenticationService.models.AuthenticationRequest;
import com.moviecruiser.AuthenticationService.models.AuthenticationResponse;
import com.moviecruiser.AuthenticationService.services.MyUserDetailsService;
import com.moviecruiser.AuthenticationService.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*")
public class HelloResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

//	@RequestMapping("/user")
//	public String user() {
//		return ("Hello World");
//	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or Password", e);
		}

		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
	
	@RequestMapping(value="/saveregister",method=RequestMethod.POST)
	 public ResponseEntity<?> postMovie(@RequestBody AuthenticationRequest authenticationRequest)
	 {
		AuthenticationRequest saveduser = myUserDetailsService.registerUser(authenticationRequest);
		 ResponseEntity responseEntity = new ResponseEntity<AuthenticationRequest>(saveduser, HttpStatus.CREATED);
		 
		 return responseEntity;
	 }
	
	

}
