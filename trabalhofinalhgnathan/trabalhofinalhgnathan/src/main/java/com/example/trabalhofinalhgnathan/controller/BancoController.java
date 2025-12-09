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

@RestController
@RequestMapping("/bancos")
public class BancoController {

	private final BancoService bancoService;

	@Autowired
	public BancoController(BancoService bancoService) {
		this.bancoService = bancoService;
	}

	@GetMapping
	public ResponseEntity<List<Banco>> getAll() {
		List<Banco> bancos = bancoService.findAll();
		return ResponseEntity.ok(bancos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Banco> getById(@PathVariable Integer id) {
		Optional<Banco> banco = bancoService.findById(id);
		return banco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<Banco> getByCodigo(@PathVariable Integer codigo) {
		Optional<Banco> banco = bancoService.findByCodigo(codigo);
		return banco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/cnpj/{cnpj}")
	public ResponseEntity<Banco> getByCnpj(@PathVariable String cnpj) {
		Optional<Banco> banco = bancoService.findByCnpj(cnpj);
		return banco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Banco> cadastrar(@RequestBody @Valid Banco banco) {
		try {
			Banco novo = bancoService.cadastrar(banco);
			return ResponseEntity.status(HttpStatus.CREATED).body(novo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Banco> atualizar(@PathVariable Integer id, @RequestBody @Valid Banco banco) {
		try {
			Banco atualizado = bancoService.atualizar(id, banco);
			return ResponseEntity.ok(atualizado);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

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
