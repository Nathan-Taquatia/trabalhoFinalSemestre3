package com.example.trabalhofinalhgnathan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trabalhofinalhgnathan.model.Conta;

/**
 * @code ContaRepository é uma interface que estende JpaRepository para fornecer operações CRUD
 * para a entidade @link Conta.
 */
@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

	// Buscar conta pelo número
	Optional<Conta> findByNumero(Integer numero);

}

