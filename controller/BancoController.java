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

import com.example.trabalhofinalhgnathan.model.Banco;
import com.example.trabalhofinalhgnathan.service.BancoService;

import jakarta.validation.Valid;

/**
 * @code BancoController é um controlador REST que lida com as operações CRUD (Create, Read, Update, Delete)
 * para a entidade @link Banco.
 * O controlador mapeia as requisições HTTP para métodos de serviço correspondentes.
 * @author Henrique e Nathan
 * @version 1.0 (ou sei lá, fizemos muitas modificações não lembro mais qual versão é essa kk)
 * @since 2025-01-01
 */
@RestController
@RequestMapping("/bancos")
public class BancoController {

	private final BancoService bancoService;

	/**
	 * Construtor para injeção de dependência do serviço @code BancoService.
	 * @param bancoService O serviço responsável pela lógica de negócio dos bancos.
	 */
	@Autowired
	public BancoController(BancoService bancoService) {
		this.bancoService = bancoService;
	}

	/**
	 * Recupera uma lista de todos os bancos cadastrados.
	 * Mapeado para a requisição GET em /bancos.
	 * @return @code ResponseEntity contendo uma lista de objetos @link Banco e status HTTP 200 (OK).
	 */
	@GetMapping
	public ResponseEntity<List<Banco>> getAll() {
		List<Banco> bancos = bancoService.findAll();
		return ResponseEntity.ok(bancos);
	}

	/**
	 * Achar um banco pelo seu ID.
	 * Mapeado para a requisição GET em /bancos/{id}.
	 * @param id O ID do banco a ser recuperado (passado como variável de caminho).
	 * @return @code ResponseEntity contendo o objeto @link Banco e status HTTP 200 (OK) se encontrado,
	 * ou status HTTP 404 (Not Found) se o banco não existir.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Banco> getById(@PathVariable Integer id) {
		Optional<Banco> banco = bancoService.findById(id);
		return banco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Achar um banco pelo seu código (único).
	 * Mapeado para a requisição GET em /bancos/codigo/{codigo}.
	 * @param codigo O código do banco a ser recuperado (passado como variável de caminho).
	 * @return @code ResponseEntity contendo o objeto @link Banco e status HTTP 200 (OK) se encontrado,
	 * ou status HTTP 404 (Not Found) se o banco não existir.
	 */
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<Banco> getByCodigo(@PathVariable Integer codigo) {
		Optional<Banco> banco = bancoService.findByCodigo(codigo);
		return banco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Achar um banco pelo seu CNPJ (único).
	 * Mapeado para a requisição GET em /bancos/cnpj/{cnpj}.
	 * @param cnpj O CNPJ do banco a ser recuperado (passado como variável de caminho).
	 * @return @code ResponseEntity contendo o objeto @link Banco e status HTTP 200 (OK) se encontrado,
	 * ou status HTTP 404 (Not Found) se o banco não existir.
	 */
	@GetMapping("/cnpj/{cnpj}")
	public ResponseEntity<Banco> getByCnpj(@PathVariable String cnpj) {
		Optional<Banco> banco = bancoService.findByCnpj(cnpj);
		return banco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Achar bancos pelo seu nome (parcial ou completo).
	 * Mapeado para a requisição GET em /bancos/nome/{nome}.
	 * @param nome O nome (parcial ou completo) do banco a ser recuperado (passado como variável de caminho).
	 * @return @code ResponseEntity contendo uma lista de objetos @link Banco e status HTTP 200 (OK).
	 */
	@PostMapping
	public ResponseEntity<Banco> cadastrar(@RequestBody @Valid Banco banco) {
		try {
			Banco novo = bancoService.cadastrar(banco);
			return ResponseEntity.status(HttpStatus.CREATED).body(novo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Atualiza os dados de um banco existente.
	 * Mapeado para a requisição PUT em /bancos/{id}.
	 * @param id O ID do banco a ser atualizado (passado como variável de caminho).
	 * @param banco O objeto {@link Banco} com os dados atualizados (passado no corpo da requisição).
	 * @return @code ResponseEntity contendo o objeto {@link Banco} atualizado e status HTTP 200 (OK) em caso de sucesso,
	 * ou status HTTP 400 (Bad Request) em caso de falha na validação ou erro de argumento.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Banco> atualizar(@PathVariable Integer id, @RequestBody @Valid Banco banco) {
		try {
			Banco atualizado = bancoService.atualizar(id, banco);
			return ResponseEntity.ok(atualizado);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Deleta um banco pelo seu ID.
	 * Mapeado para a requisição DELETE em /bancos/{id}.
	 * @param id O ID do banco a ser deletado (passado como variável de caminho).
	 * @return @code ResponseEntity com status HTTP 204 (No Content) em caso de sucesso,
	 * status HTTP 404 (Not Found) se o banco não existir, ou status HTTP 400 (Bad Request) em caso de erro.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarBanco(@PathVariable Integer id) {
		try {
			Optional<Banco> existe = bancoService.findById(id);
			if (existe.isPresent()) {
				bancoService.deleteById(id);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
