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

@RestController
@RequestMapping("/agencias")
public class AgenciaController {

	private final AgenciaService agenciaService;

	@Autowired
	public AgenciaController(AgenciaService agenciaService) {
		this.agenciaService = agenciaService;
	}

	@GetMapping
	public ResponseEntity<List<Agencia>> getAll() {
		List<Agencia> agencias = agenciaService.findAll();
		return ResponseEntity.ok(agencias);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Agencia> getById(@PathVariable Integer id) {
		Optional<Agencia> agencia = agenciaService.findById(id);
		return agencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/numero/{numero}")
	public ResponseEntity<Agencia> getByNumero(@PathVariable Integer numero) {
		Optional<Agencia> agencia = agenciaService.findByNumero(numero);
		return agencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Agencia> cadastrar(@RequestBody @Valid Agencia agencia) {
		try {
			Agencia nova = agenciaService.cadastrar(agencia);
			return ResponseEntity.status(HttpStatus.CREATED).body(nova);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Agencia> atualizar(@PathVariable Integer id, @RequestBody @Valid Agencia agencia) {
		try {
			Agencia atualizada = agenciaService.atualizar(id, agencia);
			return ResponseEntity.ok(atualizada);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

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
