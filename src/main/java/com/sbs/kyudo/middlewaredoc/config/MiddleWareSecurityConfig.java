package com.sbs.kyudo.middlewaredoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MiddleWareSecurityConfig {
	

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails kyudo = User.builder()
                .username("kyudo")
                .password("{noop}kyudo123")
                .roles("SENDER", "EXECUTER", "MONITOR")
                .build();

        UserDetails crelanDoc = User.builder()
                .username("crelan1")
                .password("{noop}crelan123")
                .roles("EXECUTER", "MONITOR")
                .build();

        UserDetails crelanMon = User.builder()
                .username("crelan2")
                .password("{noop}crelanMon123")
                .roles("MONITOR")
                .build();

        return new InMemoryUserDetailsManager(kyudo, crelanDoc, crelanMon);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.POST, "/api/kyudo/action").hasRole("EXECUTER")
                        .requestMatchers(HttpMethod.POST, "/api/kyudo/signature").hasRole("SENDER")
						.requestMatchers(HttpMethod.GET, "/actuator/health").hasRole("MONITOR")
						.requestMatchers(HttpMethod.GET, "/actuator/info").hasRole("MONITOR")
						.requestMatchers(HttpMethod.GET, "/sbs-api-docs").hasRole("MONITOR")
        );

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
