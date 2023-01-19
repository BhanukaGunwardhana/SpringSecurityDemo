package com.learningSpring.Security;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.servlet.AuthorizeHttpRequestsDsl;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class SecurityApplication {

	
	

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	@Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
	

}
