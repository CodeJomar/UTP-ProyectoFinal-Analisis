package com.udemy.matriculas.auth;

import com.udemy.matriculas.auth.services.CustomUDS;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final CustomUDS userDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers( "/scripts/**", "/styles/**", "/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/", "/login","/registro", "/post").permitAll()
                .requestMatchers("/usuarios/**", "/index", "/eventos", "/cursos").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers("/student").hasAnyAuthority("ROLE_STUDENT")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") // ← vuelve al login
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Usar CookieCsrfTokenRepository
            )
            .userDetailsService(userDetailsService)
            .build();
    }
}
