package com.ss.lms.orchestrator.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		authBuilder.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
                "select username, password, enabled from users where username = ?")
        .authoritiesByUsernameQuery(
                "select username, authority from authorities where username = ?")
        .passwordEncoder(encoder);
		
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {

	

        http.cors().and().csrf().disable().authorizeRequests()
        	
        		.antMatchers("/borrower/**").hasAuthority("ROLE_BORROWER")
                .antMatchers("/librarian/**").hasAuthority("ROLE_LIBRARIAN")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
            	.anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        		.addFilter(new JWTAuthorizationFilter(authenticationManager()))
        		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
            

}
