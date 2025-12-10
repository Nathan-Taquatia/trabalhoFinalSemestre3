package com.example.trabalhofinalhgnathan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trabalhofinalhgnathan.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

	Optional<Conta> findByNumero(Integer numero);

}

