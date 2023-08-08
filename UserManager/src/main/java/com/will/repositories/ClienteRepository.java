package com.will.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.will.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	UserDetails findByNome(String nome);
}