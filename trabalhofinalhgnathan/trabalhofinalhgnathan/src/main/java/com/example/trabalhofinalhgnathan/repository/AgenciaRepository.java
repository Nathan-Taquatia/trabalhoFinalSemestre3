package com.example.trabalhofinalhgnathan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trabalhofinalhgnathan.model.Agencia;
import com.example.trabalhofinalhgnathan.model.Banco;

/**
 * @code AgenciaRepository é uma interface que estende JpaRepository para fornecer operações CRUD
 * para a entidade @link Agencia.
 */
@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {

	// Buscar agência pelo número
	Optional<Agencia> findByNumero(Integer numero);

	// Buscar todas as agências de um banco específico
	List<Agencia> findByBanco(Banco banco);
    
	// Buscar todas as agências pelo ID do banco
    List<Agencia> findByBancoId(Long bancoId);
	
}
