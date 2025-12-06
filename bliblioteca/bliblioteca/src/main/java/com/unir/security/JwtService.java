package com.unir.security;


import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public String extractUserId(String token) {
        return getAllClaims(token).getSubject();
    }

    public boolean isExpired(String token) {
        return getAllClaims(token).getExpiration().before(new Date());
    }

    public boolean isValid(String token) {
        return !isExpired(token);
    }

    private Claims getAllClaims(String token) {
    	return Jwts.parser()
    	        .setSigningKey(getKey())
    	        .parseClaimsJws(token)
    	        .getBody();

    }
}
