package com.will.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.will.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
