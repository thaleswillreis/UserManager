package com.will.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private static final String[] RESTRICTED_MATCHERS_GET = {
			"/clientes",
			"/clientes/**"
	};
	
	private static final String[] RESTRICTED_MATCHERS_DELETE = {
			"/clientes/**"
	};
	
	private static final String[] RESTRICTED_MATCHERS_POST = {
			"/clientes",
			"/clientes/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/auth/login/**"
	};

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
/*				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.DELETE, RESTRICTED_MATCHERS_DELETE).permitAll()//.hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, RESTRICTED_MATCHERS_POST).permitAll()//.hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, RESTRICTED_MATCHERS_GET).permitAll()//.hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
						.anyRequest().authenticated()
						)
				.build();*/
	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

    @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}