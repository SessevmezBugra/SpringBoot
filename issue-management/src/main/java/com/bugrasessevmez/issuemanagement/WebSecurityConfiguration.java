package com.bugrasessevmez.issuemanagement;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
        http.cors();
            //other config
    }
 
    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS","DELETE","PUT"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers"));
        configuration.setExposedHeaders(Arrays.asList("Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(123L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
