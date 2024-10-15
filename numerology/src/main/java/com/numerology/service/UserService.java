package com.numerology.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.numerology.dto.LoginDto;
import com.numerology.exception.EmailAlreadyExistsException;
import com.numerology.exception.InvalidCredentialsException;
import com.numerology.exception.UsernameAlreadyExistsException;
import com.numerology.model.User;
import com.numerology.repository.UserRepository;
import com.numerology.security.JwtTokenProvider;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String signup(User userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
//        	return "Username already exists!";
            throw new UsernameAlreadyExistsException("Username already exists!");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
//        	return "Email already exists!";
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        // Create a new user and save it
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());

        userRepository.save(user);
        return "User registered successfully";
    }

    public Map<String, String> login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String jwtTokenString = jwtTokenProvider.generateToken(authentication);
            System.out.println("Generated JWT Token: " + jwtTokenString);

            // Create a JSON-like response with the token
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtTokenString);
            return response;
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }
}