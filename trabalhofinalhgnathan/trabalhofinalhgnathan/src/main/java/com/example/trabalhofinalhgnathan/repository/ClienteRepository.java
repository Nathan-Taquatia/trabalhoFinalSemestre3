package com.example.trabalhofinalhgnathan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trabalhofinalhgnathan.model.Cliente;

/**
 * @code ClienteRepository é uma interface que estende JpaRepository para fornecer operações CRUD
 * para a entidade @link Cliente.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	// Buscar cliente pelo CPF
	Optional<Cliente> findByCpf(String cpf);

	// Buscar clientes pelo nome (contendo, case insensitive)
	List<Cliente> findByNomeContainingIgnoreCase(String nome);

}
