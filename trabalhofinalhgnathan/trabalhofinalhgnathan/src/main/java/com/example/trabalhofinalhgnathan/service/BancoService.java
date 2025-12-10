package com.example.trabalhofinalhgnathan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trabalhofinalhgnathan.model.Banco;
import com.example.trabalhofinalhgnathan.repository.BancoRepository;

/**
 * @code BancoService é uma classe de serviço que lida com a lógica de negócio
 * para a entidade @link Banco.
 * Ela fornece métodos para operações CRUD (Create, Read, Update, Delete)
 * e validação de dados relacionados aos bancos.
 * @author Henrique e Nathan
 * @version 1.0
 * @since 2025-01-01
 */
@Service
public class BancoService {

	private final BancoRepository repository;

	/**
	 * Construtor para injeção de dependência do repositório @code BancoRepository.
	 * @param repository O repositório responsável pelo acesso aos dados dos bancos.
	 */
	@Autowired
	public BancoService(BancoRepository repository) {
		this.repository = repository;
	}

	/**
	 * Encontra um banco pelo seu ID.
	 * @param id O ID do banco a ser recuperado.
	 * @return Um objeto @link Optional contendo o banco, se encontrado.
	 */
	public Optional<Banco> findById(Integer id) {
		return repository.findById(id);
	}

	/**
	 * Recupera todas os bancos cadastrados.
	 * @return Uma lista de objetos @link Banco.
	 */
	public List<Banco> findAll() {
		return repository.findAll();
	}

	/**
	 * Cadastra um novo banco após validar seus dados.
	 * @param banco O objeto @link Banco a ser cadastrado.
	 * @return O objeto @link Banco cadastrado.
	 * @throws IllegalArgumentException se os dados do banco forem inválidos.
	 */
	public Banco cadastrar(Banco banco) {
		validarBanco(banco);
		return repository.save(banco);
	}

	/**
	 * Salva um banco no repositório.
	 * @param banco O objeto @link Banco a ser salvo.
	 * @return O objeto @link Banco salvo.
	 */
	public Banco save(Banco banco) {
		validarBanco(banco);
		return repository.save(banco);
	}

	/**
	 * Deleta um banco pelo seu ID.
	 * @param id O ID do banco a ser deletado.
	 */
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	/**
	 * Encontra um banco pelo seu código (único).
	 * @param codigo O código do banco a ser recuperado.
	 * @return Um objeto @link Optional contendo o banco, se encontrado.
	 */
	public Optional<Banco> findByCodigo(Integer codigo) {
		return repository.findByCodigo(codigo);
	}

	/**
	 * Encontra um banco pelo seu CNPJ (único).
	 * @param cnpj O CNPJ do banco a ser recuperado.
	 * @return Um objeto @link Optional contendo o banco, se encontrado.
	 */
	public Optional<Banco> findByCnpj(String cnpj) {
		return repository.findByCnpj(cnpj);
	}

	/**
	 * Encontra bancos pelo seu nome (parcial ou completo).
	 * @param nome O nome (parcial ou completo) do banco a ser recuperado.
	 * @return Uma lista de objetos @link Banco correspondentes ao critério de busca.
	 */
	public List<Banco> findByNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}

	/**
	 * Atualiza os dados de um banco existente.
	 * @param id O ID do banco a ser atualizado.
	 * @param bancoAtualizado O objeto @link Banco com os dados atualizados.
	 * @return O objeto @link Banco atualizado.
	 * @throws IllegalArgumentException se o banco com o ID especificado não for encontrado.
	 */
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

	/**
	 * Valida os dados de um banco.
	 * @param banco O objeto @link Banco a ser validado.
	 * @throws IllegalArgumentException se algum dado for inválido.
	 */
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
		Optional<Banco> existente = repository.findByCnpj(banco.getCnpj());
		if (existente.isPresent() && (banco.getId() == 0 || existente.get().getId() != banco.getId())) {
			throw new IllegalArgumentException("CNPJ já cadastrado no sistema");
		}
	}
}
