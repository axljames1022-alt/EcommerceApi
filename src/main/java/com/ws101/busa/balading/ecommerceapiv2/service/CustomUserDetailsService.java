package com.ws101.busa.balading.ecommerceapiv2.service;
import com.ws101.busa.balading.ecommerceapiv2.model.User;
import com.ws101.busa.balading.ecommerceapiv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(">>> Trying to login with username: " + username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println(">>> User not found: " + username);
                    return new UsernameNotFoundException("User not found");
                });
        System.out.println(">>> Found user: " + user.getUsername() + " | Password hash: " + user.getPassword());
        System.out.println(">>> Enabled: " + true + " | Role: " + user.getRole());
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority(user.getRole())))
                .build();
    }
}