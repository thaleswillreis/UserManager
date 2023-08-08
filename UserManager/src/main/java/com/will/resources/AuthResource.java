package com.will.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.will.domain.Cliente;
import com.will.dto.AuthenticationDTO;
import com.will.dto.LoginResponseTokenDTO;
import com.will.security.TokenSecurityConfig;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	TokenSecurityConfig tokenSecurityConfig;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authCliente) {
		var userNamePassword = new UsernamePasswordAuthenticationToken(authCliente.nome(), authCliente.senha());
		var auth = this.authenticationManager.authenticate(userNamePassword);
		var token = tokenSecurityConfig.genereteToken((Cliente) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseTokenDTO(token));
	}
}