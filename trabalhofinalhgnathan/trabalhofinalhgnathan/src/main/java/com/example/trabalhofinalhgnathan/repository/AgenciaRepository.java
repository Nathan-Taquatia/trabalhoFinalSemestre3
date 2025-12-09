package com.example.trabalhofinalhgnathan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trabalhofinalhgnathan.model.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {

	Optional<Agencia> findByNumero(Integer numero);

}
