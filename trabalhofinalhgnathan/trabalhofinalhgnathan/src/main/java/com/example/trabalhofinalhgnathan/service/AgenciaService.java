package com.example.trabalhofinalhgnathan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trabalhofinalhgnathan.model.Agencia;
import com.example.trabalhofinalhgnathan.repository.AgenciaRepository;

@Service
public class AgenciaService {

	private final AgenciaRepository repository;

	@Autowired
	public AgenciaService(AgenciaRepository repository) {
		this.repository = repository;
	}

	public Optional<Agencia> findById(Integer id) {
		return repository.findById(id);
	}

	public List<Agencia> findAll() {
		return repository.findAll();
	}

	public Agencia cadastrar(Agencia agencia) {
		validarAgencia(agencia);
		return repository.save(agencia);
	}

	public Agencia save(Agencia agencia) {
		validarAgencia(agencia);
		return repository.save(agencia);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Optional<Agencia> findByNumero(Integer numero) {
		return repository.findByNumero(numero);
	}

	public Agencia atualizar(Integer id, Agencia agenciaAtualizada) {
		Optional<Agencia> existente = repository.findById(id);
		if (existente.isPresent()) {
			Agencia agencia = existente.get();
			if (agenciaAtualizada.getNumero() > 0) {
				agencia.setNumero(agenciaAtualizada.getNumero());
			}
			if (agenciaAtualizada.getNome() != null && !agenciaAtualizada.getNome().isEmpty()) {
				agencia.setNome(agenciaAtualizada.getNome());
			}
			if (agenciaAtualizada.getBanco() != null) {
				agencia.setBanco(agenciaAtualizada.getBanco());
			}
			return repository.save(agencia);
		}
		throw new IllegalArgumentException("Agencia com id " + id + " não encontrada");
	}

	private void validarAgencia(Agencia agencia) {
		if (agencia.getNumero() <= 0) {
			throw new IllegalArgumentException("Número da agencia não pode ser nulo ou zero");
		}
		if (agencia.getNome() == null || agencia.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome da agencia não pode ser nulo ou vazio");
		}
		if (agencia.getBanco() == null) {
			throw new IllegalArgumentException("Banco não pode ser nulo");
		}
	}
}
