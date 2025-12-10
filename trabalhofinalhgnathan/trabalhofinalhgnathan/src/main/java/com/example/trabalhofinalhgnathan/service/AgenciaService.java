package com.example.trabalhofinalhgnathan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trabalhofinalhgnathan.model.Agencia;
import com.example.trabalhofinalhgnathan.repository.AgenciaRepository;

/**
 * @code AgenciaService é uma classe de serviço que lida com a lógica de negócio
 * para a entidade @link Agencia.
 * Ela fornece métodos para operações CRUD (Create, Read, Update, Delete)
 * e validação de dados relacionados às agências.
 * @author Henrique e Nathan
 * @version 1.0
 * @since 2025-01-01
 */
@Service
public class AgenciaService {

	private final AgenciaRepository repository;

	/**
	 * Construtor para injeção de dependência do repositório @code AgenciaRepository.
	 * @param repository O repositório responsável pelo acesso aos dados das agências.
	 */
	@Autowired
	public AgenciaService(AgenciaRepository repository) {
		this.repository = repository;
	}

	/**
	 * Encontra uma agência pelo seu ID.
	 * @param id O ID da agência a ser recuperada.
	 * @return Um objeto @link Optional contendo a agência, se encontrada.
	 */
	public Optional<Agencia> findById(Integer id) {
		return repository.findById(id);
	}

	/**
	 * Recupera todas as agências cadastradas.
	 * @return Uma lista de objetos @link Agencia.
	 */
	public List<Agencia> findAll() {
		return repository.findAll();
	}

	/**
	 * Cadastra uma nova agência após validar seus dados.
	 * @param agencia O objeto @link Agencia a ser cadastrado.
	 * @return O objeto @link Agencia cadastrado.
	 * @throws IllegalArgumentException se os dados da agência forem inválidos.
	 */
	public Agencia cadastrar(Agencia agencia) {
		validarAgencia(agencia);
		return repository.save(agencia);
	}

	/**
	 * Salva uma agência no repositório.
	 * @param agencia O objeto @link Agencia a ser salvo.
	 * @return O objeto @link Agencia salvo.
	 */
	public Agencia save(Agencia agencia) {
		validarAgencia(agencia);
		return repository.save(agencia);
	}

	/**
	 * Deleta uma agência pelo seu ID.
	 * @param id O ID da agência a ser deletada.
	 */
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	/**
	 * Encontra uma agência pelo seu número (único).
	 * @param numero O número da agência a ser recuperada.
	 * @return Um objeto @link Optional contendo a agência, se encontrada.
	 */
	public Optional<Agencia> findByNumero(Integer numero) {
		return repository.findByNumero(numero);
	}

	/**
	 * Encontra agências pelo ID do banco associado.
	 * @param bancoId O ID do banco cujas agências serão recuperadas.
	 * @return Uma lista de objetos @link Agencia associados ao banco especificado.
	 */
	public List<Agencia> findByBancoId(Long bancoId) {
		return repository.findByBancoId(bancoId);
	}

	/**
	 * Encontra agências pelo ID do banco associado (usando Integer).
	 * @param bancoId O ID do banco cujas agências serão recuperadas.
	 * @return Uma lista de objetos @link Agencia associados ao banco especificado.
	 */
	public List<Agencia> findByBanco(Integer bancoId) {
		return repository.findByBancoId(bancoId.longValue());
	}

	/**
	 * Atualiza os dados de uma agência existente.
	 * @param id O ID da agência a ser atualizada.
	 * @param agenciaAtualizada O objeto @link Agencia com os dados atualizados.
	 * @return O objeto @link Agencia atualizado.
	 * @throws IllegalArgumentException se a agência com o ID especificado não for encontrada.
	 */
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

	/**
	 * Valida os dados de uma agência.
	 * @param agencia O objeto @link Agencia a ser validado.
	 * @throws IllegalArgumentException se algum dado for inválido.
	 */
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
