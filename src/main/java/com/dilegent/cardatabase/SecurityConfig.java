package com.dilegent.cardatabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dilegent.cardatabase.service.UserDetailsServiceImpl;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final UserDetailsServiceImpl userDetailsService;
	public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
	    this.userDetailsService = userDetailsService;
	}
	//	Delete this method to disable in-memory users.
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
}
