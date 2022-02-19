package com.emsi.meteo.app.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

@Override
protected void configure(HttpSecurity http) throws Exception{

    http
            .cors().and()//ENABE CORS TO COMUNICATE WTITH 2 SERVERS
            .csrf().disable()//DESACTIVE CSRF IN FORMS CUZ WE HAVE API
            .authorizeRequests()
            .antMatchers(HttpMethod.POST,"/users").permitAll()//ALLOW ONLY HTTP POST ON /USERS
            .anyRequest().authenticated();

}

}
