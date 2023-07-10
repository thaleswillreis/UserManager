package com.will.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.will.dto.AuthenticationDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity login(@Valid @RequestBody AuthenticationDTO authCliente) {
		var userNamePassword = new UsernamePasswordAuthenticationToken(authCliente.login(), authCliente.senha());
		var authentication = this.authenticationManager.authenticate(userNamePassword);
		return ResponseEntity.ok().build();
	}
}