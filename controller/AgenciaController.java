package com.example.trabalhofinalhgnathan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trabalhofinalhgnathan.model.Agencia;
import com.example.trabalhofinalhgnathan.service.AgenciaService;

import jakarta.validation.Valid;

/**
 * @code AgenciaController é um controlador REST que lida com as operações CRUD (Create, Read, Update, Delete)
 * para a entidade @link Agencia.
 * O controlador mapeia as requisições HTTP para métodos de serviço correspondentes.
 * @author Henrique e Nathan
 * @version 1.0 (ou sei lá, fizemos muitas modificações não lembro mais qual versão é essa kk)
 * @since 2025-01-01
 */
@RestController
@RequestMapping("/agencias")
public class AgenciaController {

	private final AgenciaService agenciaService;

	/**
     * Construtor para injeção de dependência do serviço @code AgenciaService.
     * @param agenciaService O serviço responsável pela lógica de negócio das agências.
     */
	@Autowired
	public AgenciaController(AgenciaService agenciaService) {
		this.agenciaService = agenciaService;
	}
	
	/**
     * Recupera uma lista de todas as agências cadastradas.
     * Mapeado para a requisição GET em /agencias.
     * @return @code ResponseEntity contendo uma lista de objetos @link Agencia e status HTTP 200 (OK).
     */
	@GetMapping
	public ResponseEntity<List<Agencia>> getAll() {
		List<Agencia> agencias = agenciaService.findAll();
		return ResponseEntity.ok(agencias);
	}

	/**
     * Achar uma agência pelo seu ID.
     * Mapeado para a requisição GET em /agencias/{id}.
     * @param id O ID da agência a ser recuperada (passado como variável de caminho).
     * @return @code ResponseEntity contendo o objeto @link Agencia e status HTTP 200 (OK) se encontrado,
     * ou status HTTP 404 (Not Found) se a agência não existir.
     */
	@GetMapping("/{id}")
	public ResponseEntity<Agencia> getById(@PathVariable Integer id) {
		Optional<Agencia> agencia = agenciaService.findById(id);
		return agencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
     * Achar uma agência pelo seu número (único).
     * Mapeado para a requisição GET em /agencias/numero/{numero}.
     * @param numero O número da agência a ser recuperada (passado como variável de caminho).
     * @return @code ResponseEntity contendo o objeto @link Agencia e status HTTP 200 (OK) se encontrado,
     * ou status HTTP 404 (Not Found) se a agência não existir.
     */
	@GetMapping("/numero/{numero}")
	public ResponseEntity<Agencia> getByNumero(@PathVariable Integer numero) {
		Optional<Agencia> agencia = agenciaService.findByNumero(numero);
		return agencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
     * Cadastra uma nova agência.
     * Mapeado para a requisição POST em /agencias.
     * @param agencia O objeto {@link Agencia} a ser cadastrado (passado no corpo da requisição).
     * @return @code ResponseEntity contendo o objeto @link Agencia cadastrado e status HTTP 201 (Created) em caso de sucesso,
     * ou status HTTP 400 (Bad Request) em caso de falha na validação ou erro de argumento.
     */
	@PostMapping
	public ResponseEntity<Agencia> cadastrar(@RequestBody @Valid Agencia agencia) {
		try {
			Agencia nova = agenciaService.cadastrar(agencia);
			return ResponseEntity.status(HttpStatus.CREATED).body(nova);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
     * Atualiza os dados de uma agência existente.
     * Mapeado para a requisição PUT em /agencias/{id}.
     * @param id O ID da agência a ser atualizada (passado como variável de caminho).
     * @param agencia O objeto {@link Agencia} com os dados atualizados (passado no corpo da requisição).
     * @return @code ResponseEntity contendo o objeto {@link Agencia} atualizado e status HTTP 200 (OK) em caso de sucesso,
     * ou status HTTP 400 (Bad Request) em caso de falha na validação ou erro de argumento.
     */
	@PutMapping("/{id}")
	public ResponseEntity<Agencia> atualizar(@PathVariable Integer id, @RequestBody @Valid Agencia agencia) {
		try {
			Agencia atualizada = agenciaService.atualizar(id, agencia);
			return ResponseEntity.ok(atualizada);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Deleta uma agência pelo seu ID.
	 * Mapeado para a requisição DELETE em /agencias/{id}.
	 * @param id O ID da agência a ser deletada (passado como variável de caminho).
	 * @return @code ResponseEntity com status HTTP 204 (No Content) em caso de sucesso,
	 * status HTTP 404 (Not Found) se a agência não existir, ou status HTTP 400 (Bad Request) em caso de erro.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarAgencia(@PathVariable Integer id) {
		try {
			Optional<Agencia> existe = agenciaService.findById(id);
			if (existe.isPresent()) {
				agenciaService.deleteById(id);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
