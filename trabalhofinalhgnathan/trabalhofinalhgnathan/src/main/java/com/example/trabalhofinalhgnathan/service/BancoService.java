package com.example.trabalhofinalhgnathan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trabalhofinalhgnathan.model.Banco;
import com.example.trabalhofinalhgnathan.repository.BancoRepository;

@Service
public class BancoService {

	private final BancoRepository repository;

	@Autowired
	public BancoService(BancoRepository repository) {
		this.repository = repository;
	}

	public Optional<Banco> findById(Integer id) {
		return repository.findById(id);
	}

	public List<Banco> findAll() {
		return repository.findAll();
	}

	public Banco cadastrar(Banco banco) {
		validarBanco(banco);
		return repository.save(banco);
	}

	public Banco save(Banco banco) {
		validarBanco(banco);
		return repository.save(banco);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Optional<Banco> findByCodigo(Integer codigo) {
		return repository.findByCodigo(codigo);
	}

	public Optional<Banco> findByCnpj(String cnpj) {
		return repository.findByCnpj(cnpj);
	}

	public Banco atualizar(Integer id, Banco bancoAtualizado) {
		Optional<Banco> existente = repository.findById(id);
		if (existente.isPresent()) {
			Banco banco = existente.get();
			if (bancoAtualizado.getNome() != null && !bancoAtualizado.getNome().isEmpty()) {
				banco.setNome(bancoAtualizado.getNome());
			}
			if (bancoAtualizado.getCodigo() > 0) {
				banco.setCodigo(bancoAtualizado.getCodigo());
			}
			if (bancoAtualizado.getCnpj() != null && !bancoAtualizado.getCnpj().isEmpty()) {
				banco.setCnpj(bancoAtualizado.getCnpj());
			}
			return repository.save(banco);
		}
		throw new IllegalArgumentException("Banco com id " + id + " não encontrado");
	}

	private void validarBanco(Banco banco) {
		if (banco.getNome() == null || banco.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome do banco não pode ser nulo ou vazio");
		}
		if (banco.getCodigo() <= 0) {
			throw new IllegalArgumentException("Código do banco não pode ser nulo ou zero");
		}
		if (banco.getCnpj() == null || banco.getCnpj().isEmpty()) {
			throw new IllegalArgumentException("CNPJ não pode ser nulo ou vazio");
		}
		// Validar se CNPJ já existe (evitar duplicatas, exceto se for a mesma entidade na atualização)
		Optional<Banco> existente = repository.findByCnpj(banco.getCnpj());
		if (existente.isPresent() && (banco.getId() == 0 || existente.get().getId() != banco.getId())) {
			throw new IllegalArgumentException("CNPJ já cadastrado no sistema");
		}
	}
}
