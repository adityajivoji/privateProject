package com.numerology.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/home")
public class HomeController {

	@GetMapping("/homepage")
    public ResponseEntity<String> homepage() {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to Vastu!");
    }


}
