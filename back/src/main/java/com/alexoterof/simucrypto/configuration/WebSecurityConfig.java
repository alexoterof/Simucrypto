package com.alexoterof.simucrypto.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
        	.disable()
        	.cors()
        	.and()
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests(configurer ->
      		configurer
            .antMatchers(
                      "/error",
                      "/user/login",
                      "/user/register",
                      "/socket/**",
                      "/app/send/message/**"
            			)
            .permitAll()
			.anyRequest()
			.authenticated()
			)
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
    


}