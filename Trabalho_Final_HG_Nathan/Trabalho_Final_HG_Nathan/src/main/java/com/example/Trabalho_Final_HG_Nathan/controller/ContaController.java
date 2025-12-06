package com.example.trabalhofinalhgnathan.controller;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.trabalhofinalhgnathan.dto.TransacaoDTO;
import com.example.trabalhofinalhgnathan.model.Conta;
import com.example.trabalhofinalhgnathan.service.ContaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/conta-controller")*/
public class ContaController {
    /* 
    @Autowired
    private final ContaService contaService;

    public ContaController() {
    this.contaService = contaService;
    }

    
    @PostMapping("/cadastrar")
    public ResponseEntity<Conta> cadastrar(@RequestBody Conta conta) {
        
        try {
            Conta contaCadastrado = contaService.cadastrar(conta);
            return ResponseEntity.status(HttpStatus.CREATED).body(contaCadastrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        
        return null;
    }
    

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable int id) {
        boolean removed = contaService.deleteById(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    

    
    @GetMapping("/search/{numero}")
    public String buscaPorNumero(@PathVariable("numero") int numero, Model model) {
        Conta busca = contaService.findByNumero(numero);
        model.addAttribute("numero", busca);
        return "search-conta";
    }
    

    

    @PostMapping("/contas/{id}/sacar")
    public ResponseEntity<Conta> sacar(@PathVariable Integer id, @RequestBody @Valid TransacaoDTO transacao) {
    try {
        Conta atualizada = contaService.sacar(id, transacao.getValor());
        return ResponseEntity.ok(atualizada);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().build(); // saldo insuficiente ou valor inválido
    }
}

    @PostMapping("/contas/{id}/depositar")
    public ResponseEntity<Conta> depositar(@PathVariable Integer id, @RequestBody @Valid TransacaoDTO transacao) {
    try {
        Conta atualizada = contaService.depositar(id, transacao.getValor());
        return ResponseEntity.ok(atualizada);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }
}
 */   

}
