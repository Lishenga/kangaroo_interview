package com.kangaroo.interview.v1.security;

import com.kangaroo.interview.v1.filters.JwtRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
    throws Exception {
    auth.userDetailsService(authenticationService);
  }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
      .ignoring()
      .antMatchers(
        "/swagger-ui.html",
//              "/kangaroo_interview/**",
//        "/api/1.0/**",
        "/webjars/**",
        "/heathcheck",
        "/authenticate",
        "/api/1.0/auth/authenticateuser",
        "/api/1.0/auth/registeruser",
        "/swagger-ui/index.html?url=/api-docs&validatorUrl=#/",
        "/swagger-ui/**",
        "/api-docs",
        "/swagger-resources/**",
        "/swagger-resources",
        "/api/clients/createclient"
      );
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .csrf()
      .disable()
      .authorizeRequests()
      .antMatchers(
        "/swagger-ui.html",
        "/webjars/**",
//        "/kangaroo_interview/**",
//              "/api/1.0/**",
        "/heathcheck",
        "/authenticate",
        "/api/1.0/auth/authenticateuser",
        "/api/1.0/auth/registeruser",
        "/swagger-ui/index.html?url=/api-docs&validatorUrl=#/",
        "/swagger-ui/**",
        "/api-docs",
        "/swagger-resources/**",
        "/swagger-resources",
        "/api/clients/createclient"
      )
      .permitAll()
      .and()
      .exceptionHandling()
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity.addFilterBefore(
      jwtRequestFilter,
      UsernamePasswordAuthenticationFilter.class
    );
  }
}
