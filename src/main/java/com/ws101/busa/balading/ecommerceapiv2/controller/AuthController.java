package com.ws101.busa.balading.ecommerceapiv2.controller;

import com.ws101.busa.balading.ecommerceapiv2.dto.RegisterRequest;
import com.ws101.busa.balading.ecommerceapiv2.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }
}