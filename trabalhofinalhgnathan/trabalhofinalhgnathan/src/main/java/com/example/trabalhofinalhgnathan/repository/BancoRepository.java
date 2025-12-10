package com.example.trabalhofinalhgnathan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trabalhofinalhgnathan.model.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Integer> {

	Optional<Banco> findByCodigo(Integer codigo);

	Optional<Banco> findByCnpj(String cnpj);

	List<Banco> findByNomeContainingIgnoreCase(String nome);

}
