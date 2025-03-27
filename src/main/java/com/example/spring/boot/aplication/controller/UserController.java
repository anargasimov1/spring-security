package com.example.spring.boot.aplication.controller;

import com.example.spring.boot.aplication.Models.UserEntity;
import com.example.spring.boot.aplication.jwt.JwtService;
import com.example.spring.boot.aplication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class UserController {

    private final JwtService service;
    private final UserService userService;

    public UserController(JwtService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/token")
    public ResponseEntity<String> index(@RequestBody UserEntity user) {
        return ResponseEntity.ok(service.generateToken(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request) {
        return request.getSession().getId();
    }
}
