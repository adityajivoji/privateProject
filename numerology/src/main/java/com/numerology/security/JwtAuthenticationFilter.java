package com.numerology.security;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Logging
        System.out.println("JWT Authentication Filter called");

        // Get JWT token from the request header
        String token = getTokenFromRequest(request);
        System.out.println("Token: " + token);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            System.out.println("Authenticated User: " + username);

            // Load user associated with the token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (userDetails == null) {
                System.out.println("User not found: " + username);
                // Optionally, set response status here
                // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return; // Stop further processing
            }
            else {
            	System.out.println("User found: " + userDetails.getUsername());
            }

            // Create authentication token
            UsernamePasswordAuthenticationToken authentication =
            	    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            	SecurityContextHolder.getContext().setAuthentication(authentication);


            // Set authentication in context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            org.springframework.security.core.Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication2.isAuthenticated()) {
                CustomUserDetails user = (CustomUserDetails) authentication2.getPrincipal();
                // Check if user is null
                if (user == null) {
                	System.out.println("User is null authentication != null && authentication2.isAuthenticated() passed");
                }
                else {
                	System.out.println("user is not null");
                }
                String email = user.getEmail(); // This should now be safe
                // Proceed with your logic
                System.out.println(email);
            }
            else {
            	System.out.println("User is not authenticated authentication != null && authentication2.isAuthenticated()");
            }
        }

        filterChain.doFilter(request, response);
    }

    // Helper method to get token from the request header
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }
}