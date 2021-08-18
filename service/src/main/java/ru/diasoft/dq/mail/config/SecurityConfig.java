package ru.diasoft.dq.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@EnableResourceServer
public class SecurityConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.enabled:false}")
    private boolean securityEnabled;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if(securityEnabled) {
            http
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/", "/api/v1/status", "/csrf", "/api/v2/api-docs/**", "/v2/api-docs/**", "/api/v3/api-docs/**", "/v3/api-docs/**", "/swagger-resources/configuration/ui", "/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/configuration/security", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**").permitAll()
                .antMatchers("/**").authenticated();
        } else {
            http.authorizeRequests().antMatchers("/**").permitAll();
            http.headers().frameOptions().disable();
        }
    }

}

