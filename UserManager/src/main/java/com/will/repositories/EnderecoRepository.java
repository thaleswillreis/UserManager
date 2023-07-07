package com.will.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.will.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
