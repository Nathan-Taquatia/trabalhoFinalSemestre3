package com.example.trabalhofinalhgnathan.controller;

import java.util.List;

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

import com.example.trabalhofinalhgnathan.dto.TransacaoDTO;
import com.example.trabalhofinalhgnathan.model.Conta;
import com.example.trabalhofinalhgnathan.service.ContaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public ResponseEntity<List<Conta>> getAll() {
        return ResponseEntity.ok(contaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getById(@PathVariable Integer id) {
        return contaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Conta> getByNumero(@PathVariable Integer numero) {
        return contaService.findByNumero(numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Conta> cadastrar(@RequestBody @Valid Conta conta) {
        try {
            Conta saved = contaService.cadastrar(conta);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable Integer id) {
        boolean removed = contaService.deleteById(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/sacar")
    public ResponseEntity<Conta> sacar(@PathVariable Integer id, @RequestBody @Valid TransacaoDTO transacao) {
        try {
            Conta atualizada = contaService.sacar(id, transacao.getValor());
            return ResponseEntity.ok(atualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // saldo insuficiente ou valor inválido
        }
    }

    @PostMapping("/{id}/depositar")
    public ResponseEntity<Conta> depositar(@PathVariable Integer id, @RequestBody @Valid TransacaoDTO transacao) {
        try {
            Conta atualizada = contaService.depositar(id, transacao.getValor());
            return ResponseEntity.ok(atualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizar(@PathVariable Integer id, @RequestBody @Valid Conta conta) {
        return contaService.findById(id)
                .map(existing -> {
                    conta.setId(id);
                    Conta saved = contaService.save(conta);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}

