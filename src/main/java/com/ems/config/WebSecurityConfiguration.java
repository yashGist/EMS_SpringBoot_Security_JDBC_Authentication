package com.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

    /**
     * Creates a bean for the password encoder.
     * Passwords in the database must be encoded using BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures Spring Security to use your database for authentication.
     * It uses the standard schema (users and authorities tables).
     */
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    /**
     * Configures all HTTP security rules for the application.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Only users with the 'MANAGER' role can access URLs starting with /delete
                        .requestMatchers("/delete/**").hasRole("MANAGER")
                        // All other requests require the user to be authenticated
                        .anyRequest().authenticated()
                )
                // Enable form-based login with default settings
                .formLogin(Customizer.withDefaults())
                // Configure custom logout URLs
                .logout(logout -> logout
                        .logoutUrl("/logoutMe")
                        .logoutSuccessUrl("/loggedout")
                )
                // Disabling CSRF for simplicity.
                // NOTE: It's generally not recommended to disable CSRF in production applications.
                .csrf(csrf -> csrf.disable())

                // This part handles access denied errors and redirects to your custom page
                .exceptionHandling(exception ->
                        exception.accessDeniedPage("/access-denied")
                );

        return http.build();
    }
}