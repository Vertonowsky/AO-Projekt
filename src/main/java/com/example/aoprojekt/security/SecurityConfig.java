package com.example.aoprojekt.security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import com.example.aoprojekt.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

  private final UserService userService;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
        c -> {
          c.requestMatchers(toH2Console()).permitAll();
          c.requestMatchers("/css/**").permitAll();
          c.requestMatchers("/login1").permitAll().anyRequest().authenticated();
        });
    http.oauth2Login(
        c -> {
          c.loginPage("/login1");
          c.successHandler(
              new AuthenticationSuccessHandler() {

                @Override
                public void onAuthenticationSuccess(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    Authentication authentication)
                    throws IOException, ServletException {
                  OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

                  String email = oAuth2User.getAttribute("email");

                  userService.processPostLogin(email);
                  response.sendRedirect("/");
                }
              });
        });
    http.logout(c -> Customizer.withDefaults());
    http.csrf(c -> c.ignoringRequestMatchers(toH2Console()));
    http.headers(c -> c.frameOptions(frame -> frame.disable()));

    return http.build();
  }
}
