package com.learningSpring.Security.Configure;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {
    private static final String [] white_list_url={"/hello"
		,"/register"
		,"/resendVerificationtoken"
		,"/resendVerificationTokenWithUserName"
		,"/verifyPassWordResting"
		,"/resetPassWordWithUserName"};
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.
			cors().
			and().
			csrf().
			disable().
			authorizeHttpRequests().
			antMatchers(white_list_url).permitAll();

		return http.build();
		
	}
}
