package com.spring.spring_rest_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.spring_rest_security.service.CustomUserDetailsService;

/**
 * Configures Spring Security rules, password hashing, 
 * and authentication settings.
 */
@EnableWebSecurity 
@Configuration 
public class SecurityConfig {

    /**
     * Initialization
     */
    private CustomUserDetailsService userDetailsService;

    /**
     * Constructor
     */
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.setUserDetailsService(userDetailsService);
    }

    /**
     * Getter
     */
    public CustomUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    /**
     * Setter
     */
    public void setUserDetailsService
    (CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Provides a BCrypt encoder for securely hashing and verifying passwords.
     */
    @Bean 
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the authentication manager used to process login requests.
     */
    @Bean 
    AuthenticationManager authenticationManager
    (AuthenticationConfiguration authentication) throws Exception {
        return authentication.getAuthenticationManager();
    }

    /**
     * Defines the security filter chain: disables CSRF, 
     * allows public access to auth endpoints, secures all other routes, 
     * and enables HTTP Basic authentication.
     */
    @Bean 
    SecurityFilterChain filterChain(HttpSecurity security)
    throws Exception {
        return security
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                                    .requestMatchers("/api/auth/**")
                                    .permitAll()
                                    .anyRequest().authenticated()
                                    )
            .httpBasic(Customizer.withDefaults())
            .build();
    }
}
