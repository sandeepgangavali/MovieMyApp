package com.moviecruiser.AuthenticationService.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.moviecruiser.AuthenticationService.services.MyUserDetailsService;
import com.moviecruiser.AuthenticationService.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	
	@Autowired
	private MyUserDetailsService  userDetailsService;
	
	@Autowired
	private JwtUtil jwtutil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader=request.getHeader("Authorization");
		
		String username=null;
		String jwt=null;
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer "))
		{
			jwt=authorizationHeader.substring(7);
			username=jwtutil.extractUsername(jwt);
		}
		
		if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails myUserDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(jwtutil.validateToken(jwt, myUserDetails)) {
				UsernamePasswordAuthenticationToken UsernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(myUserDetails,null,myUserDetails.getAuthorities());
				UsernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
