package com.emsi.meteo.app.ws.security;

import com.emsi.meteo.app.ws.services.UserService;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Data
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


@Override
protected void configure(HttpSecurity http) throws Exception{


    http
            .cors().and()//ENABE CORS TO COMUNICATE WTITH 2 SERVERS
            .csrf().disable()//DESACTIVE CSRF IN FORMS CUZ WE HAVE API
            .authorizeRequests()
            .antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL).permitAll()//ALLOW ONLY HTTP POST ON /USERS
            .anyRequest().authenticated()
            .and().addFilter(new AuthenticationFilter(authenticationManager()))
    ;

}
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
