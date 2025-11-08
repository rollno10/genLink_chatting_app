package com.binarybachelor.genlink.security;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtService{

    private final String SECRET = "binarybachelorsecretkeyformyapp2004";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;
    private SecretKey key;

    @PostConstruct
    public void init(){
        byte[] decodedKey = 
        Base64.getDecoder().decode(Base64.getEncoder().encodeToString(SECRET.getBytes()));
        key = Keys.hmacShaKeyFor(decodedKey);
    }
    public String generateToken(String mobile){
        return Jwts.builder()
        .subject(mobile)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(key, Jwts.SIG.HS256)
        .compact();
    }

    public String extractMobile(String token){
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String mobile = extractMobile(token);
        return (mobile.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload();
    }
}