package com.moviecruiser.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.moviecruiser.AuthenticationService.filter.JwtRequestFilter;
import com.moviecruiser.AuthenticationService.services.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myuserDetailService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(myuserDetailService).passwordEncoder(getPasswordEncoder());
	
	}
   
	
	  @Override 
	  protected void configure(HttpSecurity http) throws Exception {
	  http.csrf().disable()
	  .authorizeRequests().
	  antMatchers("/authenticate").permitAll()
	  .antMatchers("/saveregister").permitAll()
	  .anyRequest().authenticated()
	  .and().sessionManagement()
	  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  
	  http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
	  }
	 
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	  @Bean 
	  public PasswordEncoder getPasswordEncoder( ) 
	  { 
		  return NoOpPasswordEncoder.getInstance();
	  
	  }
	 
	
	
}
