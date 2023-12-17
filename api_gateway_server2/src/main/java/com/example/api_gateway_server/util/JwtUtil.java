package com.example.api_gateway_server.util;

import java.security.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	
	public static final String SECRET = "======================BezKoder=Spring===========================";

	public void validateToken(final String token) {

		logger.info("=======>5");
//		log.debug("hello I am 2 ");
//		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
		    try {
		      Jwts.parserBuilder().setSigningKey(getSignKey()).build().parse(token);
//		      return true;
		    } catch (MalformedJwtException e) {
		      logger.error("Invalid JWT token: {}", e.getMessage());
		    } catch (ExpiredJwtException e) {
		      logger.error("JWT token is expired: {}", e.getMessage());
		    } catch (UnsupportedJwtException e) {
		      logger.error("JWT token is unsupported: {}", e.getMessage());
		    } catch (IllegalArgumentException e) {
		      logger.error("JWT claims string is empty: {}", e.getMessage());
		    }

//		    return false;
	}

	private Key getSignKey() {
		logger.debug("hello I am 3 ");
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
