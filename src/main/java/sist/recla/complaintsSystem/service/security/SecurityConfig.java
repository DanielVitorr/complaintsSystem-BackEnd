package sist.recla.complaintsSystem.service.security;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  private final CustomUserDetailsService userDetailsService;
  private final AuthenticationEntryPoint customAuthEntryPoint;

  public SecurityConfig(CustomUserDetailsService userDetailsService, CustomAuthEntryPoint customAuthEntryPoint) {
    this.userDetailsService = userDetailsService;
    this.customAuthEntryPoint = customAuthEntryPoint;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder())
        .and()
        .build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors()
        .and()
        .csrf().disable()
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(POST, "/users").permitAll()
            .requestMatchers(GET, "/users/me").authenticated()
            .requestMatchers(GET, "/users/**").permitAll()
            .requestMatchers(POST, "/complaint").authenticated()
            .requestMatchers(GET, "/complaint").authenticated()
            .anyRequest().authenticated())
        .exceptionHandling(ex -> ex
            .authenticationEntryPoint(customAuthEntryPoint))
        .httpBasic(); // se quiser manter HTTP Basic, pode ficar assim, ou remover para JWT

    return http.build();
  }
}
