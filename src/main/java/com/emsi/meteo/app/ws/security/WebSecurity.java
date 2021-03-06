package com.emsi.meteo.app.ws.security;

import com.emsi.meteo.app.ws.services.UserService;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
            .antMatchers( "/webjars/**",
                    "swagger-ui/index.html**",
                    "/v3/api-docs/**",
                    "api-docs/",
                    "swagger-ui.html",
                    "/api-docs.yaml",
                    "/swagger-resources/**").permitAll()
            .anyRequest().authenticated()
            .and().addFilter(getAuthenticationFilter())
            .addFilter(new AuthorizationFilter(authenticationManager()))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//gerer les session STATELESS for microServises == token
    ;

    }

    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/users/login"); //Override LOGIN URL of App
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
