package com.platine.myFitBuddy.config;

import com.platine.myFitBuddy.config.authentification.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
      .authorizeHttpRequests(
        auth -> {
          auth
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/proxy/**")
            .permitAll();
          auth.requestMatchers("/auth/signup", "/auth/signin").permitAll();
          auth.requestMatchers("/exercises/*/image").permitAll();
          auth.requestMatchers("/test").permitAll();
          auth.requestMatchers("/users/all").hasAuthority("ADMIN");
          auth.requestMatchers("/sessions/all").hasAuthority("ADMIN");
          auth.anyRequest().authenticated();
        }
      )
      .sessionManagement(
        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(
        jwtAuthenticationFilter,
        UsernamePasswordAuthenticationFilter.class
      )
      .csrf(AbstractHttpConfigurer::disable)
      .build();
  }
}
