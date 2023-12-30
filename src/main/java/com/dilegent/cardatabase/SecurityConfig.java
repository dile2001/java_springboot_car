package com.dilegent.cardatabase;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.
  EnableWebSecurity;

/*
 * The @Configuration and @EnableWebSecurity annotations 
 * switch off the default web security configuration, 
 * and we can define our own configuration in this class.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
}