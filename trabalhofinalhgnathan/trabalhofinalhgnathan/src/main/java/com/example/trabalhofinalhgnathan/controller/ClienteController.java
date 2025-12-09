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

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> getAll() {
		List<Cliente> clientes = clienteService.findAll();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteService.findById(id);
		return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Cliente> getByCpf(@PathVariable String cpf) {
		Optional<Cliente> cliente = clienteService.findByCpf(cpf);
		return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
		try {
			Cliente novo = clienteService.cadastrar(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(novo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
		try {
			Cliente atualizado = clienteService.atualizar(id, cliente);
			return ResponseEntity.ok(atualizado);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

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
