package com.stackroute.moviecruiserapp.util;

import java.util.Date;
import java.util.function.Function;

/*import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class ValidateMethodUtil {
	
	private String SECRET_KEY="itc_infotech#movie#stackroute";
	
	public boolean validateToken(String token) {
		
		return  !isTokenExpired(token);
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}


}
