package com.ws101.busa.balading.ecommerceapiv2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test-auth")
    @PreAuthorize("hasRole('ADMIN')")
    public String testAuth() {
        return "You are admin";
    }
}