package com.example.trabalhofinalhgnathan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trabalhofinalhgnathan.model.Cliente;
import com.example.trabalhofinalhgnathan.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository repository;

	@Autowired
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}

	public Optional<Cliente> findById(Integer id) {
		return repository.findById(id);
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente cadastrar(Cliente cliente) {
		validarCliente(cliente);
		return repository.save(cliente);
	}

	public Cliente save(Cliente cliente) {
		validarCliente(cliente);
		return repository.save(cliente);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Optional<Cliente> findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

	public Cliente atualizar(Integer id, Cliente clienteAtualizado) {
		Optional<Cliente> existente = repository.findById(id);
		if (existente.isPresent()) {
			Cliente cliente = existente.get();
			if (clienteAtualizado.getNome() != null && !clienteAtualizado.getNome().isEmpty()) {
				cliente.setNome(clienteAtualizado.getNome());
			}
			if (clienteAtualizado.getCpf() != null && !clienteAtualizado.getCpf().isEmpty()) {
				cliente.setCpf(clienteAtualizado.getCpf());
			}
			return repository.save(cliente);
		}
		throw new IllegalArgumentException("Cliente com id " + id + " não encontrado");
	}

	private void validarCliente(Cliente cliente) {
		if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
		}
		if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
			throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
		}
		// Validar se CPF já existe (evitar duplicatas)
		Optional<Cliente> existente = repository.findByCpf(cliente.getCpf());
		if (existente.isPresent() && existente.get().getId() != cliente.getId()) {
			throw new IllegalArgumentException("CPF já cadastrado no sistema");
		}
	}
}
