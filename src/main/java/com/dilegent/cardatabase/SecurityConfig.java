package com.dilegent.cardatabase;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dilegent.cardatabase.service.UserDetailsServiceImpl;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final UserDetailsServiceImpl userDetailsService;
	public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
	    this.userDetailsService = userDetailsService;
	}
	// this method is to use in-memory users.
	// @Bean
	// public InMemoryUserDetailsManager userDetailsService() {
	//     UserDetails user = User.builder().username("user").
	//         password(passwordEncoder().encode("password"))
	//         .roles("USER").build();
	//     return new InMemoryUserDetailsManager(user);
	// }
		
	// Add a new configureGlobal method to enable users from the database.

	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception
	{
	  auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}	
	 @Bean
	public PasswordEncoder passwordEncoder() {
	     return new BCryptPasswordEncoder();
	}
	 @Bean
    public AuthenticationManager uthenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	     
		 http
		 	.csrf((csrf) -> csrf.disable())
	        .sessionManagement(
	        	sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		)
	        .authorizeHttpRequests(
	        	authorizeHttpRequests ->
	        		authorizeHttpRequests
	        		.requestMatchers(HttpMethod.POST, "/login")
		         	.permitAll()
		         	.anyRequest()
		         	.authenticated()
	        );
	     return http.build();
	 }
}
