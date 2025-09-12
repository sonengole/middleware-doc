package com.sbs.kyudo.middlewaredoc.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
@Configuration
public class SecurityConfig {
	
    private  SecurityPropertiesConfig securityProperties;

    public SecurityConfig(SecurityPropertiesConfig securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
    	List<UserDetails> users = securityProperties.getUsers().stream()
            .map(u -> User.withUsername(u.getUsername())
                    .password(u.getPassword())
                    .roles(u.getRoles().split(","))
                    .build())
            .collect(Collectors.toList());
    	//System.out.println("SNN   kyudo123 ---->" + encoder.encode("kyudo123"));
    	//System.out.println("SNN   crelandoc123 ---->" + encoder.encode("crelandoc123"));
    	//System.out.println("SNN   crelanmon123 ---->" + encoder.encode("crelanmon123"));
    	//System.out.println("SNN   sone ---->" + encoder.encode("sone"));

        return new InMemoryUserDetailsManager(users);
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
