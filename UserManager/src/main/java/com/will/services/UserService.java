package com.will.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.will.domain.Cliente;

public class UserService {

	public static Cliente authenticated() {
		try {
			return (Cliente) SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}