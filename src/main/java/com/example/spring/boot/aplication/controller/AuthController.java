package com.example.spring.boot.aplication.controller;

import com.example.spring.boot.aplication.Models.UserEntity;
import com.example.spring.boot.aplication.jwt.JwtResponse;
import com.example.spring.boot.aplication.jwt.JwtService;
import com.example.spring.boot.aplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService service;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtService service) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.service = service;
    }


    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> secured(@RequestBody UserEntity user) {
        userService.loadUserByUsername(user.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        return ResponseEntity.ok(new JwtResponse(service.generateToken(user)));
    }

    @GetMapping("/token")
    public ResponseEntity<?> token(@RequestParam String token) {
        return ResponseEntity.ok(service.getClaimsFromToken(token));
    }
}
