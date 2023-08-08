package com.will.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.will.domain.Cliente;

@Service
public class TokenSecurityConfig {

	@Value("${jwt.token.secret}")
	private String secret;
	
	@Value("${jwt.token.expiration}")
	private Long expiration;
	
	@Value("${jwt.token.timezone}")
	private String timezone;

	public String genereteToken(Cliente user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("usermanager-api")
					.withSubject(user.getNome())
					.withExpiresAt(expirationToken())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("An error occurred during token generation", e);
		}
	}

	public String tokenValidation(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("usermanager-api")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException e) {
			return "";
		}
	}

	private Instant expirationToken() {
		return LocalDateTime.now()
				.plusMinutes(expiration)
				.toInstant(ZoneOffset.of(timezone));
	}
}