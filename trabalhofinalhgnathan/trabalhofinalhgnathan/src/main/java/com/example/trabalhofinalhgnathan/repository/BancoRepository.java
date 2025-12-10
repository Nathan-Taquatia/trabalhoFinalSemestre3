package com.example.trabalhofinalhgnathan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trabalhofinalhgnathan.model.Banco;

/**
 * @code BancoRepository é uma interface que estende JpaRepository para fornecer operações CRUD
 * para a entidade @link Banco.
 */
@Repository
public interface BancoRepository extends JpaRepository<Banco, Integer> {

	// Buscar banco pelo código
	Optional<Banco> findByCodigo(Integer codigo);

	// Buscar banco pelo CNPJ
	Optional<Banco> findByCnpj(String cnpj);

	// Buscar bancos pelo nome (contendo, case insensitive)
	List<Banco> findByNomeContainingIgnoreCase(String nome);

}
