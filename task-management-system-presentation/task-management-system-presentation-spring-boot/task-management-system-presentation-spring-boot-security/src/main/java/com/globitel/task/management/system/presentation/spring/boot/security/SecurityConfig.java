package com.globitel.task.management.system.presentation.spring.boot.security;

import com.globitel.task.management.system.core.token.factory.TokenUseCaseFactory;
import com.globitel.task.management.system.core.user.factory.UserUseCaseFactory;
import com.globitel.task.management.system.core.user.factory.response.UserSecurityResponse;
import com.globitel.task.management.system.presentation.spring.boot.filter.TaskManagementTokenValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,TaskManagementTokenValidationFilter taskManagementTokenValidationFilter) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/users/login").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(taskManagementTokenValidationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public UserDetailsService userDetailsService(UserUseCaseFactory userUseCaseFactory) {
        return new TMSUserDetailsService(userUseCaseFactory);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public TaskManagementTokenValidationFilter taskManagementTokenValidationFilter(ObjectMapper objectMapper, TokenUseCaseFactory tokenUseCaseFactory, UserUseCaseFactory  userUseCaseFactory, UserDetailsService  userDetailsService) {
        return new TaskManagementTokenValidationFilter( userDetailsService, tokenUseCaseFactory, userUseCaseFactory,objectMapper);
    }

    @Bean
    public TMSSpringAuthenticator tmsSpringAuthenticator(AuthenticationManager authenticationManager,UserUseCaseFactory userUseCaseFactory) {
        return new TMSSpringAuthenticator(authenticationManager,userUseCaseFactory);
    }

}
