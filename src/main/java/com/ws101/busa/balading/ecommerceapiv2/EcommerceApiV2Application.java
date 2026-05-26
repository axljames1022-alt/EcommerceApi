package com.ws101.busa.balading.ecommerceapiv2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication
public class EcommerceApiV2Application {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApiV2Application.class, args);
    }
}