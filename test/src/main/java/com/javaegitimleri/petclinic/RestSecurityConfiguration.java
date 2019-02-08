package com.javaegitimleri.petclinic;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
public class RestSecurityConfiguration extends AbstractSecurityConfiguration {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/rest/**").access("hasRole('EDITOR')");
		http.csrf().disable();
		http.httpBasic();
	}
}
