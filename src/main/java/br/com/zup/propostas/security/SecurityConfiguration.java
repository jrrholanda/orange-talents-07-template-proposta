package br.com.zup.propostas.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers(HttpMethod.POST, "/api/propostas").hasAuthority("SCOPE_proposta-escopo")
                .antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAuthority("SCOPE_proposta-escopo")
                .antMatchers(HttpMethod.POST, "/api/cartoes/**").hasAuthority("SCOPE_proposta-escopo")
                .antMatchers(HttpMethod.GET, "/actuator/prometheus").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/health").permitAll()
                .anyRequest().authenticated()).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
