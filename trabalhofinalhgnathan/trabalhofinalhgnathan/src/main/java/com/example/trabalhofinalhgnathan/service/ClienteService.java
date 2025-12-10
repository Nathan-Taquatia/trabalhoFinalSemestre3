package com.example.trabalhofinalhgnathan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trabalhofinalhgnathan.model.Cliente;
import com.example.trabalhofinalhgnathan.repository.ClienteRepository;

/**
 * @code ClienteService é uma classe de serviço que lida com a lógica de negócio
 * para a entidade @link Cliente.
 * Ela fornece métodos para operações CRUD (Create, Read, Update, Delete)
 * e validação de dados relacionados aos clientes.
 * @author Henrique e Nathan
 * @version 1.0
 * @since 2025-01-01
 */
@Service
public class ClienteService {

	private final ClienteRepository repository;

	/**
	 * Construtor para injeção de dependência do repositório @code ClienteRepository.
	 * @param repository O repositório responsável pelo acesso aos dados dos clientes.
	 */
	@Autowired
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}

	/**
	 * Encontra um cliente pelo seu ID.
	 * @param id O ID do cliente a ser recuperado.
	 * @return Um objeto @link Optional contendo o cliente, se encontrado.
	 */
	public Optional<Cliente> findById(Integer id) {
		return repository.findById(id);
	}

	/**
	 * Recupera todas os clientes cadastrados.
	 * @return Uma lista de objetos @link Cliente.
	 */
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	/**
	 * Cadastra um novo cliente após validar seus dados.
	 * @param cliente O objeto @link Cliente a ser cadastrado.
	 * @return O objeto @link Cliente cadastrado.
	 * @throws IllegalArgumentException se os dados do cliente forem inválidos.
	 */
	public Cliente cadastrar(Cliente cliente) {
		validarCliente(cliente);
		return repository.save(cliente);
	}

	/**
	 * Salva um cliente no repositório.
	 * @param cliente O objeto @link Cliente a ser salvo.
	 * @return O objeto @link Cliente salvo.
	 */
	public Cliente save(Cliente cliente) {
		validarCliente(cliente);
		return repository.save(cliente);
	}

	/**
	 * Deleta um cliente pelo seu ID.
	 * @param id O ID do cliente a ser deletado.
	 */
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	/**
	 * Encontra um cliente pelo seu CPF (único).
	 * @param cpf O CPF do cliente a ser recuperado.
	 * @return Um objeto @link Optional contendo o cliente, se encontrado.
	 */
	public Optional<Cliente> findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

	/**
	 * Encontra clientes pelo seu nome (parcial ou completo).
	 * @param nome O nome (parcial ou completo) do cliente a ser recuperado.
	 * @return Uma lista de objetos @link Cliente correspondentes ao critério de busca.
	 */
	public List<Cliente> findByNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}

	/**
	 * Atualiza os dados de um cliente existente.
	 * @param id O ID do cliente a ser atualizado.
	 * @param clienteAtualizado O objeto @link Cliente com os dados atualizados.
	 * @return O objeto @link Cliente atualizado.
	 * @throws IllegalArgumentException se o cliente com o ID especificado não for encontrado.
	 */
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

	/**
	 * Valida os dados de um cliente.
	 * @param cliente O objeto @link Cliente a ser validado.
	 * @throws IllegalArgumentException se algum dado for inválido.
	 */
	private void validarCliente(Cliente cliente) {
		if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
		}
		if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
			throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
		}
		Optional<Cliente> existente = repository.findByCpf(cliente.getCpf());
		if (existente.isPresent() && existente.get().getId() != cliente.getId()) {
			throw new IllegalArgumentException("CPF já cadastrado no sistema");
		}
	}
}
