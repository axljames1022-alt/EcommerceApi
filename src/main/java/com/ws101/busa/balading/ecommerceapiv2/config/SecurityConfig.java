package com.ws101.busa.balading.ecommerceapiv2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

/**
 * Security configuration for session-based authentication.
 * Uses cookies and server-side HTTP sessions to authenticate users.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Enables @PreAuthorize annotations on controllers
public class SecurityConfig {

    /**
     * Main security filter chain configuration.
     * Defines which endpoints are public and which require authentication.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for API testing. DO NOT use this in production
                .csrf(csrf -> csrf.disable())

                // Authorization rules for endpoints
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints - no login required
                        .requestMatchers("/login", "/api/v1/auth/register", "/css/**", "/js/**", "/favicon.ico").permitAll()
                        // Admin only endpoints
                        .requestMatchers("/admin.html", "/api/v1/auth/admin/me").hasRole("ADMIN")
                        // User and Admin endpoints
                        .requestMatchers("/checkout.html", "/api/v1/auth/user/me", "/api/v1/products").hasAnyRole("USER", "ADMIN")
                        // All other endpoints require authentication
                        .anyRequest().authenticated()
                )

                // Form login configuration for browser login
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home.html", true)
                        .permitAll()
                )

                // Logout configuration
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                // Use HTTP session for storing authentication state
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );

        return http.build();
    }

    /**
     * Custom HTTP firewall to allow semicolon in URLs.
     * Spring Security 7 blocks semicolons by default for security.
     */
    @Bean
    public HttpFirewall allowSemicolonFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        return firewall;
    }

    /**
     * Password encoder bean using BCrypt hashing algorithm.
     * Passwords are hashed before saving to database.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}