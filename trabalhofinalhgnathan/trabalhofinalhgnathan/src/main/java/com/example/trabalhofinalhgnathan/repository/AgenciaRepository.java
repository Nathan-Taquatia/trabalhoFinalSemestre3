package com.example.trabalhofinalhgnathan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trabalhofinalhgnathan.model.Agencia;
import com.example.trabalhofinalhgnathan.model.Banco;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {

	Optional<Agencia> findByNumero(Integer numero);

	List<Agencia> findByBanco(Banco banco);
    
    List<Agencia> findByBancoId(Long bancoId);
	
}
