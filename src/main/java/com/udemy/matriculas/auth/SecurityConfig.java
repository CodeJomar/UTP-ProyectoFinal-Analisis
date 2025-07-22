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
                .requestMatchers("/", "/registro", "/usuario", "/login", "/index", "/eventos", "/docentes", "/cursos", "/alumnos").permitAll()
                .requestMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers("/student").hasAnyAuthority("ROLE_STUDENT")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/?auth", true)
                .failureUrl("/?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logoutExitoso")
                .permitAll()
            )
            .userDetailsService(userDetailsService)
            .build();
    }
}
