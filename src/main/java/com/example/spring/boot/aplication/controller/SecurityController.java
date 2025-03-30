package com.example.spring.boot.aplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecurityController {

    @GetMapping("/home")
    public String home() {
        return "this is security home";
    }
}
