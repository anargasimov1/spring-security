package com.example.spring.boot.aplication.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class UserController {

    @GetMapping("/home")
    public String home(HttpServletRequest request) {
        return request.getSession().getId();
    }
}
