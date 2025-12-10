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

import com.example.trabalhofinalhgnathan.model.Cliente;
import com.example.trabalhofinalhgnathan.service.ClienteService;

import jakarta.validation.Valid;

/**
 * @code ClienteController é um controlador REST que lida com as operações CRUD (Create, Read, Update, Delete)
 * para a entidade @link Cliente.
 * O controlador mapeia as requisições HTTP para métodos de serviço correspondentes.
 * @author Henrique e Nathan
 * @version 1.0 (ou sei lá, fizemos muitas modificações não lembro mais qual versão é essa kk)
 * @since 2025-01-01
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	/**
	 * Construtor para injeção de dependência do serviço @code ClienteService.
	 * @param clienteService O serviço responsável pela lógica de negócio dos clientes.
	 */
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	/**
	 * Recupera uma lista de todos os clientes cadastrados.
	 * Mapeado para a requisição GET em /clientes.
	 * @return @code ResponseEntity contendo uma lista de objetos @link Cliente e status HTTP 200 (OK).
	 */
	@GetMapping
	public ResponseEntity<List<Cliente>> getAll() {
		List<Cliente> clientes = clienteService.findAll();
		return ResponseEntity.ok(clientes);
	}

	/**
	 * Achar um cliente pelo seu ID.
	 * Mapeado para a requisição GET em /clientes/{id}.
	 * @param id O ID do cliente a ser recuperado (passado como variável de caminho).
	 * @return @code ResponseEntity contendo o objeto @link Cliente e status HTTP 200 (OK) se encontrado,
	 * ou status HTTP 404 (Not Found) se o cliente não existir.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteService.findById(id);
		return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Achar um cliente pelo seu CPF (único).
	 * Mapeado para a requisição GET em /clientes/cpf/{cpf}.
	 * @param cpf O CPF do cliente a ser recuperado (passado como variável de caminho).
	 * @return @code ResponseEntity contendo o objeto @link Cliente e status HTTP 200 (OK) se encontrado,
	 * ou status HTTP 404 (Not Found) se o cliente não existir.
	 */
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Cliente> getByCpf(@PathVariable String cpf) {
		Optional<Cliente> cliente = clienteService.findByCpf(cpf);
		return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Achar clientes pelo seu nome (parcial ou completo).
	 * Mapeado para a requisição GET em /clientes/nome/{nome}.
	 * @param nome O nome (parcial ou completo) do cliente a ser recuperado (passado como variável de caminho).
	 * @return @code ResponseEntity contendo uma lista de objetos @link Cliente e status HTTP 200 (OK).
	 */
	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
		try {
			Cliente novo = clienteService.cadastrar(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(novo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Atualiza os dados de um cliente existente.
	 * Mapeado para a requisição PUT em /clientes/{id}.
	 * @param id O ID do cliente a ser atualizado (passado como variável de caminho).
	 * @param cliente O objeto {@link Cliente} com os dados atualizados (passado no corpo da requisição).
	 * @return @code ResponseEntity contendo o objeto {@link Cliente} atualizado e status HTTP 200 (OK) em caso de sucesso,
	 * ou status HTTP 400 (Bad Request) em caso de falha na validação ou erro de argumento.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
		try {
			Cliente atualizado = clienteService.atualizar(id, cliente);
			return ResponseEntity.ok(atualizado);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Deleta um cliente pelo seu ID.
	 * Mapeado para a requisição DELETE em /clientes/{id}.
	 * @param id O ID do cliente a ser deletado (passado como variável de caminho).
	 * @return @code ResponseEntity com status HTTP 204 (No Content) em caso de sucesso,
	 * status HTTP 404 (Not Found) se o cliente não existir, ou status HTTP 400 (Bad Request) em caso de erro.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Integer id) {
		try {
			Optional<Cliente> existe = clienteService.findById(id);
			if (existe.isPresent()) {
				clienteService.deleteById(id);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
