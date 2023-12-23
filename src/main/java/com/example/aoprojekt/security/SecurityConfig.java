package com.example.aoprojekt.security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
        c -> {
          c.requestMatchers(toH2Console()).permitAll();
          c.requestMatchers("/").permitAll().anyRequest().authenticated();
        });
    http.oauth2Login(c -> Customizer.withDefaults());
    http.logout(c -> Customizer.withDefaults());
    http.csrf(c -> c.ignoringRequestMatchers(toH2Console()));
    http.headers(c -> c.frameOptions(frame -> frame.disable()));

    return http.build();
  }
}
