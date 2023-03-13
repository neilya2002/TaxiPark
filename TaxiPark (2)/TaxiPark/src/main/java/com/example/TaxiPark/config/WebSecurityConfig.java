package com.example.TaxiPark.config;

import com.example.TaxiPark.service.RegistrationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private RegistrationService registrationService;

  public WebSecurityConfig(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/registration",
            "/addUser",
            "/static",
            "/users/*",
            "/users/address/*",
            "/users/getFile/*",
            "/users/change-address",
            "/dispetcher/drivers/*",
            "/dispetcher/drivers/status/*",
            "/dispetcher/orders/*",
            "/dispetcher/orders/status/*")
        .permitAll()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(registrationService)
        .passwordEncoder(NoOpPasswordEncoder.getInstance());
  }
}
