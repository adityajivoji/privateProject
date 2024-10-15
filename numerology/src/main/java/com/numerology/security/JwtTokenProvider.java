package com.numerology.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    // Generate a secure SecretKey for HS512 algorithm
    private SecretKey getSecretKey() {
        // Ensure the secret key is at least 512 bits
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());  // Ensure the byte array is 64 bytes (512 bits)
    }

    // Generate JWT token
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSecretKey()) // Use SecretKey generated for HS512
                .compact();
    }

    // Get username from the token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())  // Use SecretKey
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSecretKey())  // Use SecretKey
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // Handle exceptions as needed
        }
    }
}