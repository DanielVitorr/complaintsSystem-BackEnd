package sist.recla.complaintsSystem.security;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(POST, "/users").permitAll()     // Permite criar usuários
        .requestMatchers(GET, "/users/**").permitAll()   // Permite buscar usuários
        .requestMatchers(POST, "/complaint").authenticated() //Permite criar reclamações
        .requestMatchers(GET, "/complaint").authenticated() //Permite criar reclamações
        .anyRequest().authenticated()                    // Todo o resto precisa de autenticação
      )
      .httpBasic(); // Autenticação via Basic Auth

    return http.build();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
