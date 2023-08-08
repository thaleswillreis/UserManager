package com.will.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.will.repositories.ClienteRepository;

@Service
public class UserDetailsServiceImplementation implements  UserDetailsService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = clienteRepository.findByNome(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}
}